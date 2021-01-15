import { Field, Float, ID, ObjectType } from "type-graphql";
import { PartType } from "../../resolvers/enums/part-type";
import { Node } from "./node";
import { QuestionSummary } from "./question-summary";

@ObjectType({
  description: "Object representing PartSummary",
  implements: [Node],
})
export class PartSummary implements Node {
  @Field(() => ID)
  id: string;

  @Field(() => PartType, { nullable: false })
  type: PartType;

  @Field(() => Float, { nullable: true })
  averageScore?: number;

  @Field(() => Float, { nullable: true })
  totalScore?: number;

  @Field(() => [QuestionSummary], { nullable: false })
  questionSummaries: QuestionSummary[]; // QuestionSummary
}
