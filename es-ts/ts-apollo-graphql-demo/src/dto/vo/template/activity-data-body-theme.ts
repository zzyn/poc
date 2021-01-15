import { Field, ObjectType } from "type-graphql";
import { ActivityDataMediaImage } from "./activity-data-media-image";

@ObjectType({
  description: "Object representing theme of body of activity data",
})
export class ActivityDataBodyTheme {
  @Field(() => [ActivityDataMediaImage], { nullable: true })
  backgroundImages: ActivityDataMediaImage[];
}
