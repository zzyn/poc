import { Field, Int, ObjectType } from "type-graphql";
import { ActivityDataMedia } from "./activity-data-media";

@ObjectType({
  description: "Object representing audio",
  implements: [ActivityDataMedia],
})
export class ActivityDataMediaAudio implements ActivityDataMedia {
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
}
