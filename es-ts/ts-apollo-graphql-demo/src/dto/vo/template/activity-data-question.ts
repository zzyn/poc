import { Field, ObjectType } from "type-graphql";
import { ActivityDataQuestionBody } from "./activity-data-question-body";

@ObjectType({
  description: "Object representing question of activity data",
})
export class ActivityDataQuestion {
  @Field({ nullable: false })
  key: string;

  @Field(() => ActivityDataQuestionBody, { nullable: false })
  body: ActivityDataQuestionBody;
}
