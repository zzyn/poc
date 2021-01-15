import { Field, ObjectType } from "type-graphql";
import { ActivityDataStimulusBodyItem } from "./activity-data-stimulus-body-item";
import { ActivityDataStimulusBodyTag } from "./activity-data-stimulus-body-tag";

@ObjectType({
  description: "Object representing stimulu body of activity data",
})
export class ActivityDataStimulusBody {
  @Field(() => ActivityDataStimulusBodyItem, { nullable: true })
  item: ActivityDataStimulusBodyItem;

  @Field(() => ActivityDataStimulusBodyTag, { nullable: true })
  tags: ActivityDataStimulusBodyTag;
}
