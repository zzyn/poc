import { Timestamp } from "typeorm";

export interface SkillScore {
  Code: string;
  Score: number;
  TotalScore: number;
  DateTaken?: Timestamp;
}

export interface PTStudentSkillScore {
  StudentId: number;
  BookKey: string;
  BookCode: string;
  BookName: string;
  UnitKey: string;
  UnitCode: string;
  UnitName: string;
  PTKey: string;
  PTInstanceKey: string;
  IsOverwritten: boolean;
  PTTestBy: number;
  PTTotalScore?: number;
  TestKind: string;
  SkillScores: SkillScore[];
}
