import { Field, Int, ObjectType } from "type-graphql";
import { ActivityDataMediaAudio } from "./activity-data-media-audio";
import { ActivityDataMediaImage } from "./activity-data-media-image";
import { ActivityDataQuestionBodyOptionTextsItem } from "./activity-data-question-body-option-texts-item";

@ObjectType({
  description: "Object representing option of question body of activity data",
})
export class ActivityDataQuestionBodyOption {
  @Field({ nullable: false })
  id: string;

  @Field({ nullable: true })
  text: string;

  @Field(() => ActivityDataMediaAudio, { nullable: true })
  audio: ActivityDataMediaAudio;

  @Field(() => ActivityDataMediaImage, { nullable: true })
  image: ActivityDataMediaImage;

  @Field({ nullable: true })
  type: string;

  @Field(() => [ActivityDataQuestionBodyOptionTextsItem], { nullable: true })
  texts: ActivityDataQuestionBodyOptionTextsItem[];

  @Field(() => [ActivityDataMediaImage], { nullable: true })
  images: ActivityDataMediaImage[];

  @Field(() => Int, { nullable: true })
  rowIndex: number;

  @Field(() => Int, { nullable: true })
  colIndex: number;
}
