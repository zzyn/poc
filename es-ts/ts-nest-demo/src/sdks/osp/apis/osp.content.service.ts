import { HttpService, Injectable } from "@nestjs/common";
import { ConfigService } from "@nestjs/config";
import { OspSdkConfig } from "../config";
import { OspPTContentDto } from "../dto/pt.content.dto";
import { PTContentPathDto } from "../dto/pt.content.path.dto";

@Injectable()
export class OspContentService {
  constructor(
    private httpService: HttpService,
    private configService: ConfigService,
  ) {}

  ospConfig: OspSdkConfig = this.configService.get<OspSdkConfig>(
    "sdk.osp",
  ) as OspSdkConfig;

  async getPTContentPath(progressTestKey: string): Promise<PTContentPathDto> {
    console.debug(
      `OspContentService.getContentPath(progressTestKey: ${progressTestKey})`,
    );

    return this.httpService
      .post<PTContentPathDto>(
        `${this.ospConfig.url}/api/v2/ProgressTestContentPath`,
        {
          ptkey: progressTestKey,
        },
      )
      .toPromise()
      .then((x) => {
        console.debug(
          `OspContentService.getContentPath: ${x.status}, ${x.data}`,
        );
        return x.data;
      })
      .catch((error) => {
        console.debug(`${error}`);
        throw error;
      });
  }

  async getPTContent(progressTestKey: string): Promise<OspPTContentDto> {
    console.debug(
      `OspContentService.getPTContent(progressTestKey: ${progressTestKey})`,
    );

    return this.httpService
      .post<OspPTContentDto>(`${this.ospConfig.url}/api/v2/PTJsonTemplate`, {
        ptkey: progressTestKey,
      })
      .toPromise()
      .then((x) => {
        console.debug(`OspContentService.getPTContent: ${x.status}, ${x.data}`);
        return x.data;
      })
      .catch((error) => {
        console.debug(`${error}`);
        throw error;
      });
  }
}
