export interface GetGeneralTestResponseDto {
  id: string;
  studentKey: string;
  status: number;
  statusDetail: string;
  metaData: unknown;
  groupKey: string;
  product: number;
  productModule: number;
  businessKey: string;
  resultKey: string;
  result: unknown;
  createBy: string;
  createdTimestamp: Date;
  updateBy: string;
  updatedTimestamp: Date;
}
