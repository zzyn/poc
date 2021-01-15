import { ApiProperty } from "@nestjs/swagger";

export class ContentMetaResponse {
  @ApiProperty()
  mapType: number;

  @ApiProperty()
  ospKey: string;

  @ApiProperty()
  contentPath: string;

  @ApiProperty()
  contentId: string;

  @ApiProperty()
  contentRevision: string;

  @ApiProperty()
  schemaVersion: number;
}
