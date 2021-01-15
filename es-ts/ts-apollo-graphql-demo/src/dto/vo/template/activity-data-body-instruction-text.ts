import { Field, ObjectType } from "type-graphql";

@ObjectType({
  description:
    "Object representing instruction text item of body of activity data",
})
export class ActivityDataBodyInstructionText {
  @Field({ nullable: false })
  text: string;
}
