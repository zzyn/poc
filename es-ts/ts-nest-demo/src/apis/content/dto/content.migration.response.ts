import { ApiProperty } from "@nestjs/swagger";
import { ContentMigrationResponseItem } from "./content.migration.item.response";

export class ContentMigrationResponse {
  @ApiProperty({ type: [ContentMigrationResponseItem] })
  items: ContentMigrationResponseItem[];
}
