import { Field, Int, ObjectType } from "type-graphql";

@ObjectType({
  description: "Object representing tags of stimulus of activity data",
})
export class ActivityDataStimulusTag {
  @Field(() => Int, { nullable: true })
  ageGroupL: number;

  @Field(() => Int, { nullable: true })
  ageGroupH: number;

  @Field(() => [String], { nullable: true })
  cefrLevels: string[];

  @Field({ nullable: true })
  features: string;
}
