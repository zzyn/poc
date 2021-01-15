import { Field, ID, InterfaceType } from "type-graphql";

@InterfaceType()
export abstract class Node {
  @Field(() => ID)
  id: string;
}
