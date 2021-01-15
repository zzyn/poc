import { Timestamp } from "typeorm";

export interface PTStudentAnswerElement {
  Detail: unknown;
  Key?: string;
  QuestionKey: string;
  TotalScore: number;
  Score: number;
  Attempts?: number;
  TotalStar?: number;
  Star?: number;
  Duration?: number;
  LocalStartStamp?: Timestamp;
  LocalEndStamp?: Timestamp;
}

export interface PTStudentAnswerDto {
  ActivityCourseKey: string;
  ActivityKey: string;
  StudentId: string;
  GroupId: string;
  Answers: PTStudentAnswerElement[];
}
