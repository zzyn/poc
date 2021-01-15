import { HttpService, Injectable } from "@nestjs/common";
import { ConfigService } from "@nestjs/config";
import { OspSdkConfig } from "../config";
import { PTStudentAnswerDto } from "../dto/pt.student.answer.dto";
import { PTStudentSkillScore } from "../dto/pt.student.skill.score.dto";
@Injectable()
export class OspStudentProgressService {
  constructor(
    private httpService: HttpService,
    private configService: ConfigService,
  ) {}

  ospConfig: OspSdkConfig = this.configService.get<OspSdkConfig>(
    "sdk.osp",
  ) as OspSdkConfig;

  async getStudentProgressTestAnswer(
    studentIdCollection: string[],
    progressTestKey: string,
  ): Promise<PTStudentAnswerDto[]> {
    console.debug(
      `OspContentService.getStudentProgressTestAnswer(studentIdCollection: ${studentIdCollection}, progressTestKey: ${progressTestKey})`,
    );

    return this.httpService
      .post<PTStudentAnswerDto[]>(
        `${this.ospConfig.url}/api/v2/StudentProgressTestAnswer`,
        {
          studentIdCollection,
          progressTestKey,
        },
      )
      .toPromise()
      .then((x) => {
        console.debug(x);
        return x.data;
      })
      .catch((error) => {
        console.debug(`${error}`);
        throw error;
      });
  }

  async getStudentSkillScore(
    studentId: string,
    progressTestKey: string,
  ): Promise<PTStudentSkillScore> {
    console.debug(
      `OspContentService.getStudentSkillScore(studentId: ${studentId}, progressTestKey: ${progressTestKey})`,
    );

    return this.httpService
      .post<PTStudentSkillScore>(
        `${this.ospConfig.url}/api/v2/StudentProgressTestAssessmentMetasGroupBySkill2`,
        {
          StudentId: studentId,
          testPrimaryKey: progressTestKey,
        },
      )
      .toPromise()
      .then((x) => {
        console.debug(x);
        return x.data;
      })
      .catch((error) => {
        console.debug(`${error}`);
        throw error;
      });
  }
}
