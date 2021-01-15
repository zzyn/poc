import {
  PTDetailedResult,
  PTStatus,
  PTStatusDetail,
} from "./general-test-pt-components";

export interface StatefulTestResponse {
  id: string;
  studentKey: string;
  status: PTStatus;
  statusDetail: PTStatusDetail;
  metaData: unknown;
  groupKey: string;
  product: number;
  productModule: number;
  businessKey: string;
  resultKey: string;
  result: PTDetailedResult;
  createBy: string;
  updateBy: string;
}
