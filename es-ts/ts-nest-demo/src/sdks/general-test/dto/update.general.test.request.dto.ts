import { PTMetaData } from "../../../apis/student-progress/dto/metadata";
import { PTStatusDetail } from "../../../apis/student-progress/dto/status.detail";
import { GeneralTestResult } from "./create.general.test.request.dto";

export interface UpdateGeneralTestRequestDto {
  id: string;
  status: number;
  statusDetail: PTStatusDetail;
  metaData: PTMetaData;
  result?: GeneralTestResult;
}
