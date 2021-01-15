import { Service } from "typedi";
import { PTActivityContentRepoResponse } from "../data-sources/content-repo/model/pt-content-repo-acitivity-response";
import { PTTestResponse } from "../data-sources/content-repo/model/pt-content-repo-test-response";
import { PTSkillCode } from "../data-sources/general-test/model/general-test-pt-components";
import { PTContent } from "../dto/vo/pt-content";
import { ActivityData } from "../dto/vo/template";
import { GraphQLCustomResolversContext } from "../graphql/context";
import { mapPartType } from "../resolvers/enums/part-type";
import { mapProductModuleToMapType } from "../resolvers/enums/product-module";

@Service()
export class ContentService {
  async getContent(
    contentPath: string,
    productModule: number,
    ctx: GraphQLCustomResolversContext,
  ): Promise<PTContent | undefined> {
    const mapType = mapProductModuleToMapType(productModule);
    const contentInfo = await ctx.dataSources.migrationPTApi.getContentInfo({
      contentPath,
      mapType,
    });

    if (contentInfo.contentId === undefined) {
      return undefined;
    }

    const testResponse = await ctx.dataSources.contentRepoApi.getContentDetails(
      [
        {
          schemaVersion: contentInfo.schemaVersion,
          contentId: contentInfo.contentId,
          contentRevision: contentInfo.contentRevision,
        },
      ],
    );

    if (testResponse.length !== 1) {
      throw new Error("No Content Found, content may not be properly migrated");
    }

    const test = testResponse[0] as PTTestResponse;
    const activityRequests = test.data.skills.flatMap((skill) => {
      return skill.activityRefs.map((activityRef) => {
        return {
          contentId: activityRef.contentId,
          contentRevision: activityRef.contentRevision,
          schemaVersion: activityRef.schemaVersion,
        };
      });
    });

    const activityResponse = await ctx.dataSources.contentRepoApi.getContentDetails(
      activityRequests,
    );

    const activityMap = activityResponse.reduce<
      Map<string, PTActivityContentRepoResponse>
    >((map, response) => {
      return map.set(
        response.contentId,
        response as PTActivityContentRepoResponse,
      );
    }, new Map<string, PTActivityContentRepoResponse>());

    const ptContent = {
      id: test.contentId,
      title: test.data.title,
      duration: test.data.duration,
      parts: test.data.skills.map((skill) => {
        return {
          type: mapPartType(skill.code as PTSkillCode),
          activities: skill.activityRefs.map((activityRef) => {
            const activityDetail = activityMap.get(activityRef.contentId);
            if (!activityDetail) {
              throw new Error("Activity Not Found");
            }

            return {
              id: activityDetail.contentId,
              meta: {
                contentId: activityDetail.contentId,
                contentRevision: activityDetail.contentRevision,
                schemaVersion: activityDetail.schemaVersion,
              },
              data: activityDetail.data as ActivityData,
            };
          }),
        };
      }),
    };

    return ptContent;
  }
}
