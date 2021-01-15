import { Field, ID, ObjectType } from "type-graphql";
import { Node } from "./node";
import { StudentQuestionResult } from "./student-question-result";

@ObjectType({
  description: "Object representing QuestionSummary",
  implements: [Node],
})
export class QuestionSummary implements Node {
  @Field(() => ID)
  id: string;

  @Field({ nullable: false })
  questionKey: string;

  @Field({ nullable: false })
  activityKey: string;

  @Field(() => [StudentQuestionResult], { nullable: false })
  studentResults: StudentQuestionResult[];
}
