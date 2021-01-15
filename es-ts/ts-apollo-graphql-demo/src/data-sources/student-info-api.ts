import {
  createDefaultCacheKey,
  KTRestDataSource,
} from "@org/custom-graphql-datasource";
import { HTTPCache } from "apollo-datasource-rest";
import { getEnvConfig } from "../config/env";
import { StudentInfo } from "./models/student-info-response";

const { studentInfoUrl, redisCache } = getEnvConfig();

export class StudentInfoApi extends KTRestDataSource {
  constructor() {
    super();
    this.baseURL = studentInfoUrl;
    this.httpCache = new HTTPCache();
  }

  async getStudentProfile(uid: string): Promise<StudentInfo> {
    const url = `api/v1/profiles/${uid}`;
    const cacheKey = createDefaultCacheKey(url);

    return this.get(
      url,
      {},
      {},
      {
        cacheable: true,
        cacheKey,
        ttl: redisCache.ttl,
      },
    );
  }
}
