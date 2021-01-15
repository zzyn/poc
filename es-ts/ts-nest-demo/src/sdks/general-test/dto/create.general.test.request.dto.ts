import { Timestamp } from "typeorm";
import { PTMetaData } from "../../../apis/student-progress/dto/metadata";
import { PTStatusDetail } from "../../../apis/student-progress/dto/status.detail";

export interface CreateGeneralTestRequestRoute {
  contentPath: string;
  contentId: string;
  contentRevision: string;
}

export interface CreateGeneralTestRequestDetailExtension {
  activityKey: string;
  skillCode: string;
}

export interface CreateGeneralTestRequestDetail {
  activityKey: string;
  activityVersion: string;
  questionKey: string;
  questionVersion: string;
  expectedScore: number;
  actualScore: number;
  answer: unknown;
  duration?: number;
  startTime?: Timestamp;
  endTime?: Timestamp;
  extension: CreateGeneralTestRequestDetailExtension;
}

// eslint-disable-next-line @typescript-eslint/no-empty-interface
export interface CreateGeneralTestRequestExtension {}

export interface GeneralTestResult {
  actualScore: number;
  duration: number;
  expectedScore: number;
  startTime?: Timestamp;
  endTime?: Timestamp;
  route: CreateGeneralTestRequestRoute;
  details: CreateGeneralTestRequestDetail[];
  extension: CreateGeneralTestRequestExtension;
}

export interface CreateGeneralTestRequestDto {
  status: number;
  statusDetail: PTStatusDetail;
  metaData: PTMetaData;
  groupKey: string;
  product: number;
  productModule: number;
  businessKey: string;
  result?: GeneralTestResult;
  studentId: string;
}
