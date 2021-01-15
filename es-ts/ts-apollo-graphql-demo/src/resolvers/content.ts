import { Arg, Ctx, Int, Query, Resolver } from "type-graphql";
import { Service } from "typedi";
import { PTContent } from "../dto/vo/pt-content";
import { GraphQLCustomResolversContext } from "../graphql/context";
import { ContentService } from "../services/content-service";

@Service()
@Resolver(() => PTContent)
export class ContentResolver {
  constructor(private readonly contentService: ContentService) {}

  @Query(() => PTContent, { nullable: true })
  async content(
    @Arg("contentPath") contentPath: string,
    @Arg("productModule", () => Int) productModule: number,
    @Ctx() ctx: GraphQLCustomResolversContext,
  ): Promise<PTContent | undefined> {
    return this.contentService.getContent(contentPath, productModule, ctx);
  }
}
