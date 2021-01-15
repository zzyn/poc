import { Field, ObjectType } from "type-graphql";

@ObjectType({ description: "Object representing a localized text" })
export class LocalizedText {
  @Field({
    description:
      "indicate the language of the text. (ru-RU, en-US, zh-CN, id-ID)",
  })
  language: string;

  @Field({ description: "localized text" })
  text: string;
}
