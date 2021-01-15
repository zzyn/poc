import { Field, ObjectType } from "type-graphql";
import { ActivityDataStimulusBody } from "./activity-data-stimulus-body";

@ObjectType({
  description: "Object representing stimulus of activity data",
})
export class ActivityDataStimulus {
  @Field({ nullable: true })
  key: string;

  @Field(() => ActivityDataStimulusBody, { nullable: true })
  body: ActivityDataStimulusBody;
}
