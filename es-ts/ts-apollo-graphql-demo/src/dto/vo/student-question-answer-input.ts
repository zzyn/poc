import { Field, Float, InputType, Int } from "type-graphql";

@InputType()
export class StudentQuestionAnswerInput {
  @Field({ nullable: false })
  activityKey: string;

  @Field({ nullable: false })
  activityVersion: string;

  @Field({ nullable: false })
  questionKey: string;

  @Field({ nullable: false })
  templateType: string;

  @Field({ nullable: true })
  detail: string;

  @Field(() => Float, { nullable: false })
  totalScore: number;

  @Field(() => Float, { nullable: false })
  score: number;

  @Field(() => Int, { nullable: true })
  duration: number;

  @Field({ nullable: true })
  localStartStamp: Date;

  @Field({ nullable: true })
  localEndStamp: Date;
}
