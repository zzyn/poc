import { Field, ID, Int, ObjectType } from "type-graphql";
import { Node } from "./node";
import { PTPart } from "./pt-part";

@ObjectType({
  description: "Object representing PTContent",
  implements: [Node],
})
export class PTContent implements Node {
  @Field(() => ID)
  id: string;

  @Field(() => String, { nullable: false })
  title: string;

  @Field(() => Int, { nullable: false })
  duration: number;

  @Field(() => [PTPart], { nullable: false })
  parts: PTPart[];
}
