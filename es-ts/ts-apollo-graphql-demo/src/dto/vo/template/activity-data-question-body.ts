import { Field, Int, ObjectType } from "type-graphql";
import { ActivityDataQuestionBodyOption } from "./activity-data-question-body-option";
import { ActivityDataQuestionBodyTag } from "./activity-data-question-body-tag";
import { ActivityDataQuestionBodyTest } from "./activity-data-question-body-test";

@ObjectType({
  description: "Object representing question body of activity data",
})
export class ActivityDataQuestionBody {
  @Field(() => Int, { nullable: false })
  version: number;

  @Field(() => [ActivityDataQuestionBodyTag], { nullable: true })
  tags: ActivityDataQuestionBodyTag[];

  @Field(() => ActivityDataQuestionBodyTest, { nullable: false })
  tests: ActivityDataQuestionBodyTest[];

  @Field(() => [[String]], { nullable: false })
  answers: string[][];

  @Field(() => ActivityDataQuestionBodyOption, { nullable: false })
  options: ActivityDataQuestionBodyOption[];

  @Field(() => Int, { nullable: true })
  additionalLength: number;
}
