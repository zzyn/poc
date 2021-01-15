import { ActivityData } from "../../../dto/vo/template";

export interface PTActivityContentRepoResponse {
  schemaVersion: number;
  contentRevision: string;
  contentId: string;
  data: ActivityData;
  domainType: string;
  entityType: string;
  id: string;
}
