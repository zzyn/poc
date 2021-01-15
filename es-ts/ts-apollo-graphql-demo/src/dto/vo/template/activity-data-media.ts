import { Field, Int, InterfaceType } from "type-graphql";

@InterfaceType()
export abstract class ActivityDataMedia {
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
}
