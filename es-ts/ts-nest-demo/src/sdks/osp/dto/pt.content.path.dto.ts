export enum PTTestKind {
  UnitQuiz = "Progress Test",
  MidTermExam = "Mid Term Exam",
  FinalExam = "Final Exam",
}

export enum OspCourse {
  SS = "smallstar",
  HF = "highflyers",
  TB = "TB",
  FR = "frontrunner",
}

export interface PTContentPathDto {
  PTKey: string;
  TestKind: PTTestKind;
  CourseCode: OspCourse;
  BookKey: string;
  BookCode: string;
  UnitKey: string;
  UnitCode: string;
}
