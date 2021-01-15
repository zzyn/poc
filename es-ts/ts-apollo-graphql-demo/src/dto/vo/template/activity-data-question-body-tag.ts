import { Field, Int, ObjectType } from "type-graphql";

@ObjectType({
  description: "Object representing tags of question of activity data",
})
export class ActivityDataQuestionBodyTag {
  @Field(() => [String], { nullable: true })
  compassTags: string[];

  @Field(() => [String], { nullable: true })
  secondaryCompassTags: string[];

  @Field({ nullable: true })
  subSkillSet: string;

  @Field(() => [String], { nullable: true })
  vocabulary: string[];

  @Field(() => Int, { nullable: true })
  rows: number;

  @Field(() => Int, { nullable: true })
  cols: number;
}
