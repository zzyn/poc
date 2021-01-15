export interface SaveContentResponseItemDto {
  contentId: string;
  contentRevision: string;
  schemaVersion: number;
  savedContentType: string;
}

export interface SaveContentResponseDto {
  savedItems: SaveContentResponseItemDto[];
}
