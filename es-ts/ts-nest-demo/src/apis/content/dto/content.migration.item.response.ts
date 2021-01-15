import { ApiProperty } from "@nestjs/swagger";

export class ContentMigrationResponseItem {
  @ApiProperty()
  mapType: string;

  @ApiProperty()
  ospKey: string;

  @ApiProperty()
  contentId: string;

  @ApiProperty()
  contentRevision: string;
}
