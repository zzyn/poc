import { KTRestDataSource } from "@org/custom-graphql-datasource";
import { HTTPCache } from "apollo-datasource-rest";
import { getEnvConfig } from "../../config/env";
import { SearchGeneralTestRequest } from "./model/search-general-test-request";
import { StatefulTestResponse } from "./model/stateful-test-response";

const { generalTestUrl, generalTestToken } = getEnvConfig();

export class GeneralTestApi extends KTRestDataSource {
  constructor() {
    super();
    this.baseURL = generalTestUrl;
    this.httpCache = new HTTPCache();
  }

  async searchGeneralTest(
    request: SearchGeneralTestRequest,
  ): Promise<StatefulTestResponse[]> {
    const response = await this.post(
      `/admin/api/v1/stateful-test/search?_withResult=true`,
      request,
      {
        headers: {
          "X-EF-ID": generalTestToken,
        },
      },
    );

    return response as StatefulTestResponse[];
  }
}
