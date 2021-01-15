import { HttpModule, Module } from "@nestjs/common";
import { ContentRepoService } from "../../sdks/content-repo/apis";
import { OspContentService } from "../../sdks/osp/apis";
import { ContentController } from "./content.controller";
import { ContentService } from "./content.service";

@Module({
  imports: [HttpModule],
  controllers: [ContentController],
  providers: [ContentService, ContentRepoService, OspContentService],
})
export class ContentModule {}
