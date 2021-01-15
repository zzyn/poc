import { Field, ID, ObjectType } from "type-graphql";
import { Node } from "./node";
import { Student } from "./student";

@ObjectType({
  description: "Object representing Student Question Result",
  implements: [Node],
})
export class StudentQuestionResult implements Node {
  @Field(() => ID)
  id: string;

  @Field({ nullable: false })
  activityKey: string;

  @Field({ nullable: false })
  questionKey: string;

  @Field(() => Student, { nullable: false })
  student: Student;

  @Field(() => [[String]], { nullable: false })
  answer: string[][];

  @Field({ nullable: false })
  isCorrect: boolean;
}
