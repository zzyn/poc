import { Field, ObjectType } from "type-graphql";
import { ActivityDataBodyInstruction } from "./activity-data-body-instruction";
import { ActivityDataBodyMapping } from "./activity-data-body-mapping";
import { ActivityDataBodyTag } from "./activity-data-body-tag";
import { ActivityDataBodyTheme } from "./activity-data-body-theme";

@ObjectType({
  description: "Object representing body of activity data",
})
export class ActivityDataBody {
  @Field(() => ActivityDataBodyInstruction, { nullable: true })
  instruction: ActivityDataBodyInstruction;

  @Field(() => [ActivityDataBodyMapping], { nullable: true })
  mappings: ActivityDataBodyMapping[];

  @Field(() => ActivityDataBodyTheme, { nullable: true })
  theme: ActivityDataBodyTheme;

  @Field(() => ActivityDataBodyTag, { nullable: true })
  tags: ActivityDataBodyTag;
}
