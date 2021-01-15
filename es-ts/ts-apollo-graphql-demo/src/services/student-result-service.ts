import { Service } from "typedi";
import {
  mapStringToPTContentSkillCode,
  PTSkillCode,
} from "../data-sources/general-test/model/general-test-pt-components";
import { StatefulTestResponse } from "../data-sources/general-test/model/stateful-test-response";
import { PartSummary } from "../dto/vo/part-summary";
import { Student } from "../dto/vo/student";
import { StudentQuestionResult } from "../dto/vo/student-question-result";
import { StudentsResultSummary } from "../dto/vo/students-result-summary";
import { GraphQLCustomResolversContext } from "../graphql/context";
import { mapPartType } from "../resolvers/enums/part-type";
import { Product } from "./enum/product";

@Service()
export class StudentResultService {
  async getStudentResultSummary(
    contentPath: string,
    productModule: number,
    studentIds: string[],
    ctx: GraphQLCustomResolversContext,
  ): Promise<StudentsResultSummary> {
    // eslint-disable-next-line max-len
    const statefulResultResponse = await ctx.dataSources.generalTestApi.searchGeneralTest(
      {
        studentKeyList: studentIds,
        productList: [Product.FR, Product.HF, Product.TB],
        productModule,
        groupKey: contentPath,
      },
    );

    const students = this.constructStudents(
      studentIds.filter((id) =>
        statefulResultResponse.find((t) => t.studentKey === id),
      ),
    );

    const ptAverageInfo = this.calculatePTAverage(statefulResultResponse);
    return Promise.resolve({
      id: contentPath,
      contentPath,
      productModule,
      completedStudentCount: ptAverageInfo.totalCompletedCount,
      totalStudentCount: studentIds.length,
      averageScore: ptAverageInfo.average,
      students,
      partSummaries: this.constructPTPartSummaries(
        statefulResultResponse,
        students,
      ),
    });
  }

  async getStudentResult(
    contentPath: string,
    productModule: number,
    studentIds: string[],
    ctx: GraphQLCustomResolversContext,
  ): Promise<StudentQuestionResult[]> {
    // eslint-disable-next-line max-len
    const statefulResultResponse = await ctx.dataSources.generalTestApi.searchGeneralTest(
      {
        studentKeyList: studentIds,
        productList: [Product.FR, Product.HF, Product.TB],
        productModule,
        groupKey: contentPath,
      },
    );

    const students = this.constructStudents(studentIds);

    return statefulResultResponse.reduce<StudentQuestionResult[]>(
      (retVal, statefulTest) => {
        if (!statefulTest.result || !statefulTest.result.details) {
          return retVal;
        }

        return retVal.concat(
          statefulTest.result.details.map((resultDetail) => {
            return {
              id: resultDetail.questionKey,
              questionKey: resultDetail.questionKey,
              activityKey: resultDetail.activityKey,
              student: students.filter((student) => {
                return student.id === statefulTest.studentKey;
              })[0],
              answer: [[JSON.stringify(resultDetail.answer.modelData)]],
              isCorrect:
                resultDetail.expectedScore === resultDetail.actualScore,
            };
          }),
        );
      },
      [],
    );
  }

  private constructStudents(studentIds: string[]): Student[] {
    return studentIds.map((studentId) => {
      return {
        id: studentId,
        localName: "",
        englishName: "",
      };
    });
  }

  private calculatePTAverage(
    statefulTestResponse: StatefulTestResponse[],
  ): { average: number | undefined; totalCompletedCount: number } {
    let totalCompletedCount = 0;
    const total = statefulTestResponse.reduce<number>(
      (result, statefulTest) => {
        if (
          statefulTest.statusDetail.result.score === undefined ||
          statefulTest.statusDetail.result.score === null
        ) {
          return result;
        }

        totalCompletedCount += 1;

        return result + statefulTest.statusDetail.result.score;
      },
      0,
    );

    const average =
      totalCompletedCount === 0
        ? undefined
        : Math.round(total / totalCompletedCount);

    return { average, totalCompletedCount };
  }

  private constructPTPartSummaries(
    statefulTestResponse: StatefulTestResponse[],
    students: Student[],
  ): PartSummary[] {
    const skillMap = [
      PTSkillCode.grammar,
      PTSkillCode.vocabulary,
      PTSkillCode.listening,
      PTSkillCode.reading,
      PTSkillCode.writing,
      PTSkillCode.speaking,
    ].reduce((result, skillCode) => {
      return result.set(skillCode, {
        summary: {
          id: skillCode,
          type: mapPartType(skillCode),
          averageScore: undefined,
          totalScore: undefined,
          questionSummaries: [],
        },
        count: 0,
        score: 0,
      });
    }, new Map<PTSkillCode, { summary: PartSummary; count: number; score: number }>());

    statefulTestResponse.reduce<
      Map<string, { summary: PartSummary; count: number }>
    >((result, statefulTest) => {
      if (
        !statefulTest.statusDetail ||
        !statefulTest.statusDetail.result ||
        !statefulTest.statusDetail.result.skills
      ) {
        return result;
      }

      statefulTest.statusDetail.result.skills.reduce<
        Map<string, { summary: PartSummary; count: number }>
      >((retVal, skill) => {
        const skillDetail = skillMap.get(
          mapStringToPTContentSkillCode(skill.code),
        );

        if (skill.score === undefined || skill.totalScore === undefined) {
          return retVal;
        }

        if (skillDetail === undefined) {
          throw new Error("Skill not found");
        }

        skillDetail.score += skill.score;
        skillDetail.count += 1;

        skillDetail.summary.averageScore =
          Math.round((skillDetail.score * 10) / skillDetail.count) / 10;
        skillDetail.summary.totalScore = skill.totalScore;

        return retVal;
      }, skillMap);

      if (
        statefulTest.result === undefined ||
        statefulTest.result.details === undefined
      ) {
        return result;
      }

      statefulTest.result.details.reduce<
        Map<string, { summary: PartSummary; count: number }>
      >((retVal, resultDetail) => {
        if (statefulTest.status !== 3 && statefulTest.status !== 4) {
          return retVal;
        }

        const skillDetail = skillMap.get(resultDetail.extension.skillCode);
        if (skillDetail === undefined) {
          throw new Error("skill not found");
        }

        const questionSummary = skillDetail.summary.questionSummaries.find(
          (summary) => {
            return (
              summary.activityKey === resultDetail.activityKey &&
              summary.questionKey === resultDetail.questionKey
            );
          },
        );

        const studentResult = {
          id: resultDetail.questionKey,
          questionKey: resultDetail.questionKey,
          activityKey: resultDetail.activityKey,
          student: students.filter((student) => {
            return student.id === statefulTest.studentKey;
          })[0],
          answer: [[JSON.stringify(resultDetail.answer.modelData)]],
          isCorrect: resultDetail.expectedScore === resultDetail.actualScore,
        };

        if (questionSummary === undefined) {
          skillDetail.summary.questionSummaries.push({
            id: resultDetail.questionKey,
            questionKey: resultDetail.questionKey,
            activityKey: resultDetail.activityKey,
            studentResults: [studentResult],
          });
        } else {
          questionSummary.studentResults.push(studentResult);
        }

        return retVal;
      }, skillMap);

      return skillMap;
    }, skillMap);

    return [
      skillMap.get(PTSkillCode.grammar)?.summary,
      skillMap.get(PTSkillCode.vocabulary)?.summary,
      skillMap.get(PTSkillCode.listening)?.summary,
      skillMap.get(PTSkillCode.reading)?.summary,
      skillMap.get(PTSkillCode.writing)?.summary,
      skillMap.get(PTSkillCode.speaking)?.summary,
    ].filter((item) => {
      return item !== undefined;
    }) as PartSummary[];
  }
}
