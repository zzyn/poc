import { MapType } from "../../resolvers/enums/product-module";

export interface MigrationPTGetContentRequest {
  mapType: MapType;
  contentPath: string;
}
