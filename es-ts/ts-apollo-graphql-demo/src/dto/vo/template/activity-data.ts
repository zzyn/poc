import { Field, ObjectType } from "type-graphql";
import { ActivityDataBody } from "./activity-data-body";
import { ActivityDataQuestion } from "./activity-data-question";
import { ActivityDataStimulus } from "./activity-data-stimulus";

@ObjectType({
  description: "Object representing data of activity",
})
export class ActivityData {
  @Field({ nullable: false })
  Key: string;

  @Field({ nullable: false })
  Title: string;

  @Field({ nullable: false })
  Type: string;

  @Field(() => ActivityDataBody, { nullable: false })
  Body: ActivityDataBody;

  @Field(() => [ActivityDataStimulus], { nullable: true })
  Stimulus: ActivityDataStimulus[];

  @Field(() => [ActivityDataQuestion], { nullable: false })
  Questions: ActivityDataQuestion[];
}
