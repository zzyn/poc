import { Field, Int, ObjectType } from "type-graphql";
import { ActivityDataMedia } from "./activity-data-media";
import { ActivityDataMediaImage } from "./activity-data-media-image";

@ObjectType({
  description: "Object representing video",
  implements: [ActivityDataMedia],
})
export class ActivityDataMediaVideo implements ActivityDataMedia {
  @Field({ nullable: false })
  id: string;

  @Field({ nullable: false })
  url: string;

  @Field(() => Int, { nullable: false })
  size: number;

  @Field({ nullable: false })
  sha1: string;

  @Field({ nullable: false })
  mimeType: string;

  @Field(() => Int, { nullable: false })
  duration: number;

  @Field({ nullable: true })
  language: string;

  @Field(() => [ActivityDataMediaImage], { nullable: true })
  thumbnails: ActivityDataMediaImage[];
}
