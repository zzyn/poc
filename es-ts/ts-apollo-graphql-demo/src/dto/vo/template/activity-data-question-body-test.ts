import { Field, ObjectType } from "type-graphql";
import { ActivityDataMediaAudio } from "./activity-data-media-audio";
import { ActivityDataMediaImage } from "./activity-data-media-image";
import { ActivityDataMediaVideo } from "./activity-data-media-video";

@ObjectType({
  description: "Object representing test of question body of activity data",
})
export class ActivityDataQuestionBodyTest {
  @Field({ nullable: true })
  text: string;

  @Field(() => [ActivityDataMediaVideo], { nullable: true })
  videos: ActivityDataMediaVideo[];

  @Field(() => ActivityDataMediaImage, { nullable: true })
  image: ActivityDataMediaImage;

  @Field(() => ActivityDataMediaAudio, { nullable: true })
  audio: ActivityDataMediaAudio;
}
