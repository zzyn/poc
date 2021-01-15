import { Field, ID, ObjectType } from "type-graphql";
import { ActivityData } from "./activity-data";
import { ActivityMeta } from "./activity-meta";

@ObjectType({
  description: "Object representing activity",
})
export class Activity {
  @Field(() => ID)
  id: string;

  @Field(() => ActivityMeta, { nullable: true })
  meta: ActivityMeta;

  @Field(() => ActivityData, { nullable: true })
  data: ActivityData;
}
