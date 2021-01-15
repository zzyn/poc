import { Ctx, Query, Resolver } from "type-graphql";
import { Service } from "typedi";
import { User } from "../dto/vo/user";
import { GraphQLCustomResolversContext } from "../graphql/context";

@Service()
@Resolver(() => User)
export class UserResolver {
  @Query(() => User)
  user(@Ctx() ctx: GraphQLCustomResolversContext): User {
    return {
      sub: ctx.user.sub,
      givenName: ctx.user.given_name,
      familyName: ctx.user.family_name,
      nickName: undefined,
      email: undefined,
      birth: undefined,
      zoneInfo: undefined,
      gender: ctx.user.gender,
      marketRegion: ctx.user.market_region,
    };
  }
}
