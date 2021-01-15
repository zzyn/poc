import { Field, Float, ID, Int, ObjectType } from "type-graphql";
import { Node } from "./node";
import { PartSummary } from "./part-summary";
import { Student } from "./student";

@ObjectType({
  description: "Object representing StudentsResultSummary",
  implements: [Node],
})
export class StudentsResultSummary implements Node {
  @Field(() => ID)
  id: string;

  @Field({ nullable: false })
  contentPath: string;

  @Field(() => Int, { nullable: false })
  productModule: number;

  @Field(() => Int, { nullable: false })
  completedStudentCount: number;

  @Field(() => Int, { nullable: false })
  totalStudentCount: number;

  @Field(() => Float, { nullable: true })
  averageScore?: number;

  @Field(() => [Student], { nullable: false })
  students: Student[];

  @Field(() => [PartSummary], { nullable: false })
  partSummaries: PartSummary[];
}
