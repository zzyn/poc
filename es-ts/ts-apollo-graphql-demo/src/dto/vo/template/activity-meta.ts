import { Field, Int, ObjectType } from "type-graphql";

@ObjectType({
  description: "Object representing meta of activity",
})
export class ActivityMeta {
  @Field({ nullable: false })
  contentId: string;

  @Field({ nullable: false })
  contentRevision: string;

  @Field(() => Int, { nullable: false })
  schemaVersion: number;
}
