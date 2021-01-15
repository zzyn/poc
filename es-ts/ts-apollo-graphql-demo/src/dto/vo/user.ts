import { Field, ID, ObjectType } from "type-graphql";
import { MarketRegion } from "../../resolvers/enums/market-region";
import { ZoneInfo } from "../../resolvers/enums/zone-info";

@ObjectType({ description: "Object representing user" })
export class User {
  @Field(() => ID)
  sub: string;

  @Field({ nullable: true })
  givenName?: string;

  @Field({ nullable: true })
  familyName?: string;

  @Field({ nullable: true })
  nickName?: string;

  @Field({ nullable: true })
  picture?: string;

  @Field({ nullable: true })
  email?: string;

  @Field({ nullable: true })
  gender?: string;

  @Field({ nullable: true })
  birth?: string;

  @Field({ nullable: true })
  zoneInfo?: ZoneInfo;

  @Field({ nullable: true })
  marketRegion?: MarketRegion;
}
