import { HttpService, Injectable } from "@nestjs/common";
import { ConfigService } from "@nestjs/config";
import { GeneralTestSdkConfig } from "../config/general.test.sdk.config";
import { CreateGeneralTestRequestDto } from "../dto/create.general.test.request.dto";
import { StatefulTestResponse } from "../dto/create.general.test.response.dto";
import { GetGeneralTestRequestDto } from "../dto/get.general.test.request.dto";
import { GetGeneralTestResponseDto } from "../dto/get.general.test.response.dto";
import { UpdateGeneralTestRequestDto } from "../dto/update.general.test.request.dto";

@Injectable()
export class GeneralTestService {
  constructor(
    private httpService: HttpService,
    private configService: ConfigService,
  ) {}

  // eslint-disable-next-line max-len
  generalTestSdkConfig: GeneralTestSdkConfig = this.configService.get<GeneralTestSdkConfig>(
    "sdk.general-test",
  ) as GeneralTestSdkConfig;

  async getGeneralTest(
    request: GetGeneralTestRequestDto,
  ): Promise<GetGeneralTestResponseDto[]> {
    const groupKey = request.groupKey.replace(/(\/)/gi, "%2F");
    console.debug(
      `${this.generalTestSdkConfig.url}admin/api/v1/stateful-test/${request.studentKey}?product=${request.product}&productModule=${request.productModule}&groupKey=${groupKey}`,
    );
    return this.httpService
      .get<GetGeneralTestResponseDto[]>(
        `${this.generalTestSdkConfig.url}admin/api/v1/stateful-test/${request.studentKey}?product=${request.product}&productModule=${request.productModule}&groupKey=${groupKey}`,
      )
      .toPromise()
      .then((x) => {
        console.debug(`GeneralTestService.getGeneralTest: ${x.data}`);
        return x.data;
      })
      .catch((error) => {
        console.log("here");
        console.debug(`${error}`);
        throw error;
      });
  }

  createGeneralTest = async (
    request: CreateGeneralTestRequestDto,
  ): Promise<StatefulTestResponse> => {
    console.debug(`GeneralTestService.createGeneralTest(request: ${request})`);
    return this.httpService
      .post<StatefulTestResponse>(
        `${this.generalTestSdkConfig.url}/admin/api/v1/stateful-test/${request.studentId}`,
        request,
        {
          headers: { "X-EF-ID": this.generalTestSdkConfig.idToken },
        },
      )
      .toPromise()
      .then((x) => {
        console.debug(`GeneralTestService.createGeneralTest: ${x.data}`);
        const response = x.data;
        response.succeeded = true;
        return response;
      })
      .catch((error) => {
        console.log(error);

        const response = {
          succeeded: false,
        } as StatefulTestResponse;
        return response;
      });
  };

  updateGeneralTest = async (
    request: UpdateGeneralTestRequestDto,
  ): Promise<StatefulTestResponse> => {
    console.debug(`GeneralTestService.updateGeneralTest(request: ${request})`);
    return this.httpService
      .patch<StatefulTestResponse>(
        `${this.generalTestSdkConfig.url}/admin/api/v1/stateful-test/${request.id}`,
        request,
        {
          headers: { "X-EF-ID": this.generalTestSdkConfig.idToken },
        },
      )
      .toPromise()
      .then((x) => {
        console.debug(`GeneralTestService.updateGeneralTest: ${x.data}`);
        const response = x.data;
        response.succeeded = true;
        return response;
      })
      .catch((error) => {
        console.log(error);

        const response = {
          succeeded: false,
        } as StatefulTestResponse;
        return response;
      });
  };
}
