import { Field, InputType } from "type-graphql";

@InputType()
export class MigrateStudentsResultInput {
  @Field({ nullable: false })
  progressTestKey: string;

  @Field(() => [String], { nullable: false })
  studentIds: string[];
}
