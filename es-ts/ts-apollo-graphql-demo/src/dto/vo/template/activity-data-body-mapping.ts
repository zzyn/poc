import { Field, ObjectType } from "type-graphql";

@ObjectType({
  description: "Object representing mapping of body of activity data",
})
export class ActivityDataBodyMapping {
  @Field({ nullable: false })
  s: string;

  @Field({ nullable: false })
  q: string;
}
