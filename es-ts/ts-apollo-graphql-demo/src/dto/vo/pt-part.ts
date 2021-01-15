import { Field, ObjectType } from "type-graphql";
import { PartType } from "../../resolvers/enums/part-type";
import { Node } from "./node";
import { Activity } from "./template";

@ObjectType({
  description: "Object representing PTPart",
  implements: [Node],
})
export class PTPart {
  @Field(() => PartType)
  type: PartType;

  @Field(() => [Activity], { nullable: false })
  activities: Activity[];
}
