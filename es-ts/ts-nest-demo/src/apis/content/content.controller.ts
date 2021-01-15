import { Body, Controller, Get, Post, Query } from "@nestjs/common";
import {
  ApiBody,
  ApiConsumes,
  ApiProduces,
  ApiResponse,
} from "@nestjs/swagger";
import { ApiException } from "../../filter";
import { ContentService } from "./content.service";
import {
  ContentMetaResponse,
  ContentMigrationRequest,
  ContentMigrationResponse,
} from "./dto";

@Controller("api/v1/content")
export class ContentController {
  constructor(private readonly contentService: ContentService) {}

  @ApiConsumes("application/json")
  @ApiProduces("application/json")
  @ApiBody({ type: ContentMigrationRequest })
  @ApiResponse({
    status: 201,
    type: ContentMigrationResponse,
    description: "success",
  })
  @Post("/migrate")
  async migrate(
    @Body() param: ContentMigrationRequest,
  ): Promise<ContentMigrationResponse> {
    return this.contentService.migrate(param);
  }

  @ApiConsumes("*/*")
  @ApiProduces("application/json")
  @ApiResponse({
    status: 200,
    type: ContentMetaResponse,
    description: "success",
  })
  @ApiResponse({ status: 204, description: "not found" })
  @Get("/meta")
  async findContentMeta(
    @Query("contentPath") contentPath: string,
    @Query("mapType") mapType: number,
  ): Promise<ContentMetaResponse> {
    const data = await this.contentService.findOneContentMeta({
      contentPath,
      mapType,
    });

    if (data === undefined) {
      throw new ApiException("not found", 204);
    } else {
      return data;
    }
  }
}
