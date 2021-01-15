import { PTContent } from "./pt.content.dto";

export interface GetContentResponseDto {
  schemaVersion: number;
  contentRevision: string;
  contentId: string;
  data: PTContent;
  domainType: string;
  entityType: string;
  id: string;
}
