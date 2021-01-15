import { Field, ID, ObjectType } from "type-graphql";

@ObjectType({ description: "Object representing student" })
export class Student {
  @Field(() => ID)
  id: string;

  @Field({ nullable: false })
  localName: string;

  @Field({ nullable: false })
  englishName: string;
}
