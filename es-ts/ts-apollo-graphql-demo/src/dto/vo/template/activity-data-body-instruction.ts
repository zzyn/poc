import { Field, ObjectType } from "type-graphql";
import { ActivityDataBodyInstructionText } from "./activity-data-body-instruction-text";

@ObjectType({
  description: "Object representing instruction of body of activity data",
})
export class ActivityDataBodyInstruction {
  @Field(() => [ActivityDataBodyInstructionText], { nullable: true })
  texts: ActivityDataBodyInstructionText[];
}
