import { Field, Int, ObjectType } from "type-graphql";

@ObjectType({
  description: "Object representing tags of body of activity data",
})
export class ActivityDataBodyTag {
  @Field({ nullable: true })
  key: string;

  @Field({ nullable: true })
  skillType: string;

  @Field(() => Int, { nullable: true })
  ageGroupL: number;

  @Field(() => Int, { nullable: true })
  ageGroupH: number;

  @Field({ nullable: true })
  activityType: string;

  @Field({ nullable: true })
  activitySubType: string;

  @Field(() => [String], { nullable: true })
  cefrLevels: string[];

  @Field(() => [String], { nullable: true })
  compassTags: string[];

  @Field(() => [String], { nullable: true })
  secondaryCompassTags: string[];

  @Field(() => [String], { nullable: true })
  totalCompassTags: string[];

  @Field({ nullable: true })
  learningFocus: string;
}
