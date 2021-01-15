import {
  createDefaultCacheKey,
  KTRestDataSource,
} from "@org/custom-graphql-datasource";
import { getEnvConfig } from "../../config/env";
import {
  GetContentDetailsRequest,
  GetContentDetailsResponse,
} from "./model/content-repo-response";

const { contentRepoUrl, redisCache } = getEnvConfig();

export class ContentRepoApi extends KTRestDataSource {
  constructor() {
    super();
    this.baseURL = contentRepoUrl;
  }

  async getContentDetails(
    request: GetContentDetailsRequest[],
  ): Promise<GetContentDetailsResponse[]> {
    const url = "api/v1/activities";

    const cacheKey = createDefaultCacheKey(url, request);

    return this.post(url, request, undefined, {
      cacheable: true,
      cacheKey,
      ttl: redisCache.contentTtl,
    });
  }
}
