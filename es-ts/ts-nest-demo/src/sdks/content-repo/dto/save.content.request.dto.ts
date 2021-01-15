export interface SaveContentRequestActivityDto {
  schemaVersion: number;
  data: any;
  contentId: string;
  contentRevision: string;
  domainType: string;
  entityType: string;
}
export interface SaveContentRequestDto {
  releaseRevision: string;
  forceUpdate: boolean;
  activities: SaveContentRequestActivityDto[];
}
