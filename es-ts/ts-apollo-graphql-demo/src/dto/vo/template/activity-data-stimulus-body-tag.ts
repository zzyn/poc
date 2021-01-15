import { Field, ObjectType } from "type-graphql";
import { ActivityDataStimulusTag } from "./activity-data-stimulus-tag";

@ObjectType({
  description: "Object representing tags of stimulus of activity data",
})
export class ActivityDataStimulusBodyTag {
  @Field(() => [String], { nullable: true })
  compassTags: string[];

  @Field(() => [String], { nullable: true })
  secondaryCompassTags: string[];

  @Field(() => ActivityDataStimulusTag, { nullable: true })
  text: ActivityDataStimulusTag;

  @Field(() => ActivityDataStimulusTag, { nullable: true })
  image: ActivityDataStimulusTag;

  @Field(() => ActivityDataStimulusTag, { nullable: true })
  audio: ActivityDataStimulusTag;

  @Field(() => ActivityDataStimulusTag, { nullable: true })
  video: ActivityDataStimulusTag;
}
