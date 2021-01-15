import { HttpService, Injectable } from "@nestjs/common";
import { ConfigService } from "@nestjs/config";
import { ContentRepoSdkConfig } from "../config";
import { SaveContentResponseDto } from "../dto";
import { GetContentRequestDto } from "../dto/get.content.request.dto";
import { GetContentResponseDto } from "../dto/get.content.response.dto";
import { SaveContentRequestDto } from "../dto/save.content.request.dto";

@Injectable()
export class ContentRepoService {
  constructor(
    private httpService: HttpService,
    private configService: ConfigService,
  ) {}

  contentRepoConfig: ContentRepoSdkConfig = this.configService.get<ContentRepoSdkConfig>(
    "sdk.content-repo",
  ) as ContentRepoSdkConfig;

  async saveContent(
    saveContentRequestDto: SaveContentRequestDto,
  ): Promise<SaveContentResponseDto> {
    console.debug(
      `ContentRepoService.saveContent(saveContentRequestDto: ${saveContentRequestDto})`,
    );

    return this.httpService
      .post<SaveContentResponseDto>(
        `${this.contentRepoConfig.url}admin/api/v1/contents`,
        saveContentRequestDto,
      )
      .toPromise()
      .then((x) => {
        console.debug(`ContentRepoService.saveContent: ${x.status}, ${x.data}`);
        return x.data;
      })
      .catch((error) => {
        console.debug(`${error}`);
        throw error;
      });
  }

  async getContent(
    request: [GetContentRequestDto],
  ): Promise<[GetContentResponseDto]> {
    console.debug(`ContentRepoService.getContent(request: ${request})`);

    return this.httpService
      .post<[GetContentResponseDto]>(
        `${this.contentRepoConfig.url}api/v1/activities`,
        request,
      )
      .toPromise()
      .then((x) => {
        console.debug(`ContentRepoService.getContent: ${x.data}`);
        return x.data;
      })
      .catch((error) => {
        console.debug(`${error}`);
        throw error;
      });
  }
}
