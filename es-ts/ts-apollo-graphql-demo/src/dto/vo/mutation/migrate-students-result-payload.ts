import { Field, ObjectType } from "type-graphql";
import { UserError } from "../../user-error/user-error";
import { StudentsResultSummary } from "../students-result-summary";

@ObjectType({ description: "Object representing the output of migrate result" })
export class MigrateStudentsResultPayload {
  @Field(() => StudentsResultSummary, { nullable: true })
  studentsResultSummary?: StudentsResultSummary;

  @Field(() => [UserError], { nullable: false })
  userErrors: UserError[];
}
