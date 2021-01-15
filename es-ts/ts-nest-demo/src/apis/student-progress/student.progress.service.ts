/* eslint-disable max-lines */
import { Injectable } from "@nestjs/common";
import { difference } from "ramda";
import { EntityManager, Timestamp } from "typeorm";
import { ContentMigrationMappingEntityRepository } from "../../db/content.migration.mapping.repository";
import { ProgressMigrationHistoryEntityRepository } from "../../db/progress.migration.history.repository";
import { ApiException } from "../../filter/error/api.exception";
import { ContentRepoService } from "../../sdks/content-repo/apis/content.repo.service";
import { GetContentResponseDto } from "../../sdks/content-repo/dto";
import {
  mapStringToPTContentSkillCode,
  PTContentSkillCode,
} from "../../sdks/content-repo/dto/pt.content.dto";
import { GeneralTestService } from "../../sdks/general-test/apis";
import {
  CreateGeneralTestRequestDetail,
  CreateGeneralTestRequestDto,
  GeneralTestResult,
} from "../../sdks/general-test/dto/create.general.test.request.dto";
import { UpdateGeneralTestRequestDto } from "../../sdks/general-test/dto/update.general.test.request.dto";
import {
  OspContentService,
  OspStudentProgressService,
} from "../../sdks/osp/apis";
import { OspCourse, PTStudentAnswerDto, PTTestKind } from "../../sdks/osp/dto";
import { PTStudentSkillScore } from "../../sdks/osp/dto/pt.student.skill.score.dto";
import {
  CURRENT_MIGRATION_VERSION,
  GeneralTestStatus,
  PTProduct,
  PTProductModule,
} from "./constant";
import { PTMetaData } from "./dto/metadata";
import { PTStatusDetail, PTStatusDetailType } from "./dto/status.detail";
import { StudentProgressMigrationRequest } from "./dto/student.progress.migration.request";
import { StudentProgressMigrationResponse } from "./dto/student.progress.migration.response";

@Injectable()
export class StudentProgressService {
  constructor(
    private contentRepoService: ContentRepoService,
    private ospContentService: OspContentService,
    private ospStudentProgressService: OspStudentProgressService,
    private generalTestService: GeneralTestService,
    private entityManager: EntityManager,
  ) {}

  async migrate(
    param: StudentProgressMigrationRequest,
  ): Promise<StudentProgressMigrationResponse> {
    // Get ospContent to map product & product module
    const ospContent = await this.ospContentService.getPTContentPath(
      param.progressTestKey,
    );

    if (ospContent === undefined || ospContent === null) {
      throw new ApiException("Content Not Found", 500);
    }

    const product = this.mapProduct(ospContent.CourseCode);
    const productModule = this.mapProductModule(ospContent.TestKind);

    console.log(`product: ${product}, productModule: ${productModule}`);

    // Get migratedMapping entry from db

    const migratedMapping = await this.entityManager
      .getCustomRepository(ContentMigrationMappingEntityRepository)
      .findMigratedMappingByOspKey(param.progressTestKey);

    if (!migratedMapping) {
      throw new ApiException("Migration Mapping Not Found", 500);
    }

    console.log(`migratedMapping.contentPath: ${migratedMapping.contentPath}`);
    const { contentPath } = migratedMapping;
    const { contentId } = migratedMapping;
    const { contentRevision } = migratedMapping;

    const existingResults = await this.entityManager
      .getCustomRepository(ProgressMigrationHistoryEntityRepository)
      .findProgressMigrationHistory(param.progressTestKey, param.studentIds);

    console.log(`existingResults: ${existingResults}`);

    const existingStudentIds = existingResults.map((existingResult) => {
      return existingResult.studentId;
    });

    // Filter student id to create <- no entry
    const needCreateStudentIds = difference(
      param.studentIds,
      existingStudentIds,
    );

    // Filter student id to update <- not all skills are filled
    const needUpdateInfo = existingResults.map((existingResult) => {
      return {
        studentKey: existingResult.studentId,
        generalTestId: existingResult.generalTestId,
        ptInstanceKey: existingResult.ptInstanceKey,
        generalTestStatus: existingResult.generalTestStatus,
      };
    });

    let allStudentIdToProcess: string[] = needCreateStudentIds;
    allStudentIdToProcess = allStudentIdToProcess.concat(
      needUpdateInfo.map((info) => info.studentKey),
    );
    console.log(`needCreateStudentIds: ${needCreateStudentIds}`);
    console.log(`needUpdateStudentIds: ${needUpdateInfo}`);

    if (needCreateStudentIds.length === 0 && needUpdateInfo.length === 0) {
      return {
        contentPath,
        productModule,
      };
    }

    // Get Paper detail
    const paperList = await this.contentRepoService.getContent([
      {
        schemaVersion: 1,
        contentId,
        contentRevision,
      },
    ]);
    console.log(`${contentId}: ${contentRevision}`);

    console.log(`paper: ${paperList}`);
    if (paperList === undefined) {
      throw new ApiException("Content Not Found", 500);
    }

    const paper = paperList[0];
    if (paper.data === undefined) {
      throw new ApiException("Content Is Ruined", 500);
    }

    const activityContentIds: string[] = [];
    const paperStructure = paper.data.skills.reduce<
      Map<string, PTContentSkillCode>
    >((retVal, skill) => {
      skill.activityRefs.forEach((activityRef) => {
        retVal.set(
          activityRef.contentId,
          mapStringToPTContentSkillCode(skill.code),
        );
        activityContentIds.push(activityRef.contentId);
      });

      return retVal;
    }, new Map<string, PTContentSkillCode>());
    console.log(`paperStructure: ${paperStructure}`);
    const migrationMappings = await this.entityManager
      .getCustomRepository(ContentMigrationMappingEntityRepository)
      .findMigratedMappings(activityContentIds);
    const ospContentIdMap = migrationMappings.reduce<Map<string, string>>(
      (retVal, element) => {
        retVal.set(element.ospKey, element.contentId);

        return retVal;
      },
      new Map<string, string>(),
    );

    ospContentIdMap.forEach((value, key) => {
      console.log(`${key}: ${value}`);
    });

    // eslint-disable-next-line max-len
    console.log(allStudentIdToProcess);
    if (allStudentIdToProcess.length === 0) {
      return {
        contentPath,
        productModule,
      };
    }

    // eslint-disable-next-line max-len
    const studentProgressTestAnswers = await this.ospStudentProgressService.getStudentProgressTestAnswer(
      allStudentIdToProcess,
      param.progressTestKey,
    );

    const studentSkillScorePromises = allStudentIdToProcess.map((studentId) => {
      return this.ospStudentProgressService.getStudentSkillScore(
        studentId,
        param.progressTestKey,
      );
    });

    const studentSkillScores = (
      await Promise.all(studentSkillScorePromises)
    ).filter((score) => {
      return score !== null;
    });

    // eslint-disable-next-line max-len
    const studentInstanceKeyMap = studentSkillScores.reduce<
      Map<number, string>
    >((map, studentSkillScore) => {
      map.set(studentSkillScore.StudentId, studentSkillScore.PTInstanceKey);

      return map;
    }, new Map<number, string>());

    console.log(`studentProgressTestAnswer: ${studentProgressTestAnswers}`);

    const createRequests = needCreateStudentIds.reduce<
      CreateGeneralTestRequestDto[]
    >((retVal, studentId) => {
      const answers = studentProgressTestAnswers.filter(
        (studentProgressTestAnswer) => {
          return studentProgressTestAnswer.StudentId === studentId;
        },
      );

      const skillScore = studentSkillScores.find((studentSkillScore) => {
        if (studentSkillScore && studentSkillScore.StudentId) {
          return studentSkillScore.StudentId.toString() === studentId;
        }

        return false;
      });

      if (!skillScore) {
        return retVal;
      }

      const mappedAnswer = this.mapAnswer(
        answers,
        ospContentIdMap,
        paperStructure,
        contentRevision,
        skillScore,
      );

      const result = this.mapResult(
        mappedAnswer.actualScore,
        mappedAnswer.duration,
        mappedAnswer.expectedScore,
        contentPath,
        contentId,
        contentRevision,
        mappedAnswer.startTime,
        mappedAnswer.endTime,
        mappedAnswer.answerDetails,
      );

      const createGeneralTestRequestDto = {
        studentId,
        status: this.mapStatus(
          mappedAnswer.skillsScore,
          skillScore.PTTotalScore,
        ),
        statusDetail: this.mapStatusDetails(
          skillScore.PTTotalScore,
          mappedAnswer.skillsScore,
          mappedAnswer.ptStatusDetailType,
        ),
        metaData: this.mapMetadata(
          paper,
          contentPath,
          contentId,
          contentRevision,
        ),
        groupKey: contentPath,
        product,
        productModule,
        businessKey: contentId,
      } as CreateGeneralTestRequestDto;

      if (createGeneralTestRequestDto.status !== GeneralTestStatus.NO_RESULT) {
        createGeneralTestRequestDto.result = result;
      }

      return retVal.concat(createGeneralTestRequestDto);
    }, []);

    const updateRequests = needUpdateInfo.reduce<UpdateGeneralTestRequestDto[]>(
      (retVal, info) => {
        const answers = studentProgressTestAnswers.filter(
          (studentProgressTestAnswer) => {
            return studentProgressTestAnswer.StudentId === info.studentKey;
          },
        );

        let skillScore = studentSkillScores.find((studentSkillScore) => {
          return studentSkillScore.StudentId.toString() === info.studentKey;
        });

        if (!skillScore) {
          if (info.generalTestStatus === GeneralTestStatus.NO_RESULT) {
            return retVal;
          }
          skillScore = ({
            StudentId: info.studentKey,
            SkillScores: [],
          } as unknown) as PTStudentSkillScore;
        }

        const mappedAnswer = this.mapAnswer(
          answers,
          ospContentIdMap,
          paperStructure,
          contentRevision,
          skillScore,
        );

        const result = this.mapResult(
          mappedAnswer.actualScore,
          mappedAnswer.duration,
          mappedAnswer.expectedScore,
          contentPath,
          contentId,
          contentRevision,
          mappedAnswer.startTime,
          mappedAnswer.endTime,
          mappedAnswer.answerDetails,
        );

        const updateGeneralTestRequestDto = {
          id: info.generalTestId,
          status: this.mapStatus(
            mappedAnswer.skillsScore,
            skillScore.PTTotalScore,
          ),
          statusDetail: this.mapStatusDetails(
            skillScore.PTTotalScore,
            mappedAnswer.skillsScore,
            mappedAnswer.ptStatusDetailType,
          ),
          metaData: this.mapMetadata(
            paper,
            contentPath,
            contentId,
            contentRevision,
          ),
        } as UpdateGeneralTestRequestDto;

        if (
          skillScore.PTInstanceKey === info.ptInstanceKey &&
          updateGeneralTestRequestDto.status === info.generalTestStatus &&
          updateGeneralTestRequestDto.status === GeneralTestStatus.NOT_COMPLETED
        ) {
          // Score is not overwritten
          return retVal;
        }

        if (
          updateGeneralTestRequestDto.status ===
            GeneralTestStatus.NOT_COMPLETED ||
          (updateGeneralTestRequestDto.status === GeneralTestStatus.COMPLETED &&
            info.generalTestStatus === GeneralTestStatus.NO_RESULT)
        ) {
          updateGeneralTestRequestDto.result = result;
        }

        return retVal.concat(updateGeneralTestRequestDto);
      },
      [],
    );

    console.log(createRequests);
    console.log(updateRequests);

    // Get existing users' result from general test
    const createGeneralTestPromises = createRequests.map(
      this.generalTestService.createGeneralTest,
    );

    const createResponses = await Promise.all(createGeneralTestPromises).catch(
      (error) => {
        throw new ApiException(error, 500);
      },
    );

    await this.entityManager
      .getCustomRepository(ProgressMigrationHistoryEntityRepository)
      .save(
        createResponses
          .filter((response) => {
            return response.succeeded === true;
          })
          .map((response) => {
            return {
              id: 0,
              studentId: response.studentKey,
              progressTestKey: param.progressTestKey,
              generalTestId: response.id,
              generalTestStatus: response.status,
              migrationVersion: CURRENT_MIGRATION_VERSION,
              migratedAt: new Date(),
              ptInstanceKey: studentInstanceKeyMap.get(
                Number(response.studentKey),
              ),
            };
          }, []),
      );

    const updateGeneralTestPromises = updateRequests.map(
      this.generalTestService.updateGeneralTest,
    );

    const updateResponse = await Promise.all(updateGeneralTestPromises).catch(
      (error) => {
        throw new ApiException(error, 500);
      },
    );

    await this.entityManager
      .getCustomRepository(ProgressMigrationHistoryEntityRepository)
      .save(
        updateResponse
          .filter((response) => {
            return response.succeeded === true;
          })
          .map((response) => {
            return {
              id: existingResults.find((result) => {
                return (
                  result.studentId === response.studentKey &&
                  result.progressTestKey.toUpperCase() ===
                    param.progressTestKey.toUpperCase()
                );
              })?.id,
              studentId: response.studentKey,
              progressTestKey: param.progressTestKey,
              generalTestId: response.id,
              generalTestStatus: response.status,
              migrationVersion: CURRENT_MIGRATION_VERSION,
              migratedAt: new Date(),
              ptInstanceKey:
                response.status === GeneralTestStatus.NO_RESULT
                  ? ""
                  : studentInstanceKeyMap.get(Number(response.studentKey)),
            };
          }),
      );
    return {
      contentPath,
      productModule,
    };
  }

  private mapProduct(courseCode: OspCourse): PTProduct {
    switch (courseCode) {
      case OspCourse.HF: {
        return PTProduct.HF;
      }
      case OspCourse.TB: {
        return PTProduct.TB;
      }
      case OspCourse.FR: {
        return PTProduct.FR;
      }
      case OspCourse.SS: {
        return PTProduct.SS;
      }
      default: {
        throw new ApiException("Wrong type", 500);
      }
    }
  }

  private mapProductModule(testKind: PTTestKind): PTProductModule {
    switch (testKind) {
      case PTTestKind.UnitQuiz: {
        return PTProductModule.UnitQuiz;
      }
      case PTTestKind.MidTermExam: {
        return PTProductModule.MidTermExam; // TODO - Not quite sure
      }
      case PTTestKind.FinalExam: {
        return PTProductModule.FinalExam; // TODO - Not quite sure
      }
      default: {
        throw new ApiException("Wrong type", 500);
      }
    }
  }

  private mapMetadata(
    paper: GetContentResponseDto,
    contentPath: string,
    contentId: string,
    contentRevision: string,
  ): PTMetaData {
    return {
      title: paper.data.title,
      duration: paper.data.duration,
      contentPath,
      contentRef: {
        contentId,
        contentRevision,
        schemaVersion: 1,
      },
    };
  }

  private mapStatus(
    skillsScore: Map<PTContentSkillCode, { hasScore: boolean }>,
    ptTotalScore: number | undefined,
  ): number {
    let hasScore = true;
    skillsScore.forEach((value, key) => {
      if (
        !value.hasScore &&
        key !== PTContentSkillCode.speaking &&
        key !== PTContentSkillCode.writing
      ) {
        hasScore = false;
      }
    });

    let status = GeneralTestStatus.NO_RESULT;
    if (hasScore && !ptTotalScore) {
      status = GeneralTestStatus.NOT_COMPLETED;
    } else if (ptTotalScore) {
      status = GeneralTestStatus.COMPLETED;
    }

    return status;
  }

  private mapStatusDetails(
    actualScore: number | undefined,
    skillsScore: Map<
      PTContentSkillCode,
      { score?: number; hasScore: boolean; totalScore?: number }
    >,
    ptStatusDetailType: PTStatusDetailType,
  ): PTStatusDetail {
    return {
      type: ptStatusDetailType,
      result: {
        score: actualScore,
        skills: [
          {
            code: PTContentSkillCode.grammar.toString(),
            score: skillsScore.get(PTContentSkillCode.grammar)?.score,
            totalScore: skillsScore.get(PTContentSkillCode.grammar)?.totalScore,
          },
          {
            code: PTContentSkillCode.vocabulary.toString(),
            score: skillsScore.get(PTContentSkillCode.vocabulary)?.score,
            totalScore: skillsScore.get(PTContentSkillCode.vocabulary)
              ?.totalScore,
          },
          {
            code: PTContentSkillCode.listening.toString(),
            score: skillsScore.get(PTContentSkillCode.listening)?.score,
            totalScore: skillsScore.get(PTContentSkillCode.listening)
              ?.totalScore,
          },
          {
            code: PTContentSkillCode.reading.toString(),
            score: skillsScore.get(PTContentSkillCode.reading)?.score,
            totalScore: skillsScore.get(PTContentSkillCode.reading)?.totalScore,
          },
          {
            code: PTContentSkillCode.speaking.toString(),
            score: skillsScore.get(PTContentSkillCode.speaking)?.score,
            totalScore: skillsScore.get(PTContentSkillCode.speaking)
              ?.totalScore,
          },
          {
            code: PTContentSkillCode.writing.toString(),
            score: skillsScore.get(PTContentSkillCode.writing)?.score,
            totalScore: skillsScore.get(PTContentSkillCode.writing)?.totalScore,
          },
        ],
      },
    };
  }

  private mapAnswer(
    answers: PTStudentAnswerDto[],
    ospContentIdMap: Map<string, string>,
    paperStructure: Map<string, PTContentSkillCode>,
    contentRevision: string,
    studentSkillScore: PTStudentSkillScore,
  ): {
    actualScore: number;
    expectedScore: number;
    duration: number;
    startTime: Timestamp;
    endTime: Timestamp;
    skillsScore: Map<
      PTContentSkillCode,
      { score?: number; hasScore: boolean; totalScore?: number }
    >;
    answerDetails: CreateGeneralTestRequestDetail[];
    ptStatusDetailType: PTStatusDetailType;
  } {
    let actualScore = 0;
    let expectedScore = 0;
    let duration = 0;
    let startTime: Timestamp = (undefined as unknown) as Timestamp;
    let endTime: Timestamp = (undefined as unknown) as Timestamp;
    const skillsScore = this.generateSkillsScore();
    const ptStatusDetailType = studentSkillScore.IsOverwritten
      ? PTStatusDetailType.traditional
      : PTStatusDetailType.digital;
    studentSkillScore.SkillScores.reduce<
      Map<
        PTContentSkillCode,
        { score?: number; hasScore: boolean; totalScore?: number }
      >
    >((retVal, skillScore) => {
      const score = skillsScore.get(
        mapStringToPTContentSkillCode(skillScore.Code),
      );

      if (score === undefined) {
        throw new ApiException("Error Mapping for skill", 500);
      }

      score.hasScore = true;
      score.score = skillScore.Score;
      score.totalScore = skillScore.TotalScore;
      return retVal;
    }, skillsScore);

    const answerDetails = answers.flatMap((answer) => {
      console.log(`answer.ActivityKey: ${answer.ActivityKey}`);
      const activityKey = ospContentIdMap.get(answer.ActivityKey);
      if (activityKey === undefined) {
        throw new ApiException("Activity Not Found", 500);
      }
      const temp = answer.Answers.map((answerDetail) => {
        const skillCode = paperStructure.get(activityKey);
        if (skillCode === undefined) {
          throw new ApiException("Activity Not Found", 500);
        }

        actualScore += answerDetail.Score;
        expectedScore += answerDetail.TotalScore;
        duration += answerDetail.Duration ? answerDetail.Duration : 0;
        if (
          startTime !== undefined &&
          answerDetail.LocalStartStamp !== undefined &&
          startTime.greaterThan(answerDetail.LocalStartStamp)
        ) {
          startTime = answerDetail.LocalStartStamp;
        }

        if (
          endTime !== undefined &&
          answerDetail.LocalEndStamp !== undefined &&
          endTime.lessThan(answerDetail.LocalEndStamp)
        ) {
          endTime = answerDetail.LocalEndStamp;
        }

        return {
          activityKey,
          activityVersion: contentRevision,
          questionKey: answerDetail.QuestionKey,
          questionVersion: contentRevision,
          expectedScore: answerDetail.TotalScore,
          actualScore: answerDetail.Score,
          answer: answerDetail.Detail,
          duration: answerDetail.Duration,
          startTime: answerDetail.LocalStartStamp,
          endTime: answerDetail.LocalEndStamp,
          extension: {
            activityKey: answer.ActivityKey,
            skillCode,
          },
        };
      });

      return temp;
    });
    return {
      actualScore,
      expectedScore,
      duration,
      answerDetails,
      startTime,
      endTime,
      skillsScore,
      ptStatusDetailType,
    };
  }

  private generateSkillsScore(): Map<
    PTContentSkillCode,
    { score?: number; hasScore: boolean; totalScore?: number }
  > {
    const skillsScore = new Map<
      PTContentSkillCode,
      { score?: number; hasScore: boolean; totalScore?: number }
    >();
    skillsScore.set(PTContentSkillCode.grammar, {
      score: undefined,
      totalScore: undefined,
      hasScore: false,
    });
    skillsScore.set(PTContentSkillCode.listening, {
      score: undefined,
      totalScore: undefined,
      hasScore: false,
    });
    skillsScore.set(PTContentSkillCode.reading, {
      score: undefined,
      totalScore: undefined,
      hasScore: false,
    });
    skillsScore.set(PTContentSkillCode.speaking, {
      score: undefined,
      totalScore: undefined,
      hasScore: false,
    });
    skillsScore.set(PTContentSkillCode.writing, {
      score: undefined,
      totalScore: undefined,
      hasScore: false,
    });
    skillsScore.set(PTContentSkillCode.vocabulary, {
      score: undefined,
      totalScore: undefined,
      hasScore: false,
    });

    return skillsScore;
  }

  private mapResult(
    actualScore: number,
    duration: number,
    expectedScore: number,
    contentPath: string,
    contentId: string,
    contentRevision: string,
    startTime: Timestamp,
    endTime: Timestamp,
    answerDetails: CreateGeneralTestRequestDetail[],
  ): GeneralTestResult {
    return {
      actualScore,
      duration,
      expectedScore,
      route: {
        contentPath,
        contentId,
        contentRevision,
      },
      startTime,
      endTime,
      details: answerDetails,
      extension: {},
    };
  }
}
