import { ApiProperty } from "@nestjs/swagger";

export class ContentMigrationRequest {
  @ApiProperty()
  progressTestKey: string;

  @ApiProperty()
  contentRevision: string;
}
