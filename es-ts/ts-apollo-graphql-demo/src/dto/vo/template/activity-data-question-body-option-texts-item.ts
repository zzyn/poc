import { Field, ObjectType } from "type-graphql";

@ObjectType({
  description:
    "Object representing option texts item of question body of activity data",
})
export class ActivityDataQuestionBodyOptionTextsItem {
  @Field({ nullable: false })
  text: string;
}
