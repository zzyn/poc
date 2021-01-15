import { ApiProperty } from "@nestjs/swagger";

export class ContentMetaRequest {
  @ApiProperty()
  mapType: number;

  @ApiProperty()
  contentPath: string;
}
