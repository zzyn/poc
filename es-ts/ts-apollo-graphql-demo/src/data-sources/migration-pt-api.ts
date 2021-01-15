import {
  createDefaultCacheKey,
  KTRestDataSource,
} from "@org/custom-graphql-datasource";
import { getEnvConfig } from "../config/env";
import { MigrationPTGetContentRequest } from "./models/migration-pt-content-request";
import { MigrationPTGetContentResponse } from "./models/migration-pt-content-response";
import { MigrationPTProgressRequest } from "./models/migration-pt-progress-request";
import { MigrationPTProgressResponse } from "./models/migration-pt-progress-response";

const { migrationPTUrl, redisCache } = getEnvConfig();

export class MigrationPTApi extends KTRestDataSource {
  constructor() {
    super();
    this.baseURL = migrationPTUrl;
  }

  async migrate(
    params: MigrationPTProgressRequest,
  ): Promise<MigrationPTProgressResponse> {
    const url = `api/v1/student-progress/migrate`;
    const cacheKey = createDefaultCacheKey(url, params);

    return this.post(
      url,
      params,
      {
        headers: {},
      },
      {
        cacheable: true,
        cacheKey,
        ttl: redisCache.ttl,
      },
    );
  }

  async getContentInfo(
    request: MigrationPTGetContentRequest,
  ): Promise<MigrationPTGetContentResponse> {
    const contentPath = request.contentPath.replace(/(\/)/gi, "%2F");
    const url = `api/v1/content/meta?contentPath=${contentPath}&mapType=${request.mapType}`;
    return this.get(
      url,
      undefined,
      {
        headers: {},
      },
      {
        cacheable: false,
      },
    );
  }
}
