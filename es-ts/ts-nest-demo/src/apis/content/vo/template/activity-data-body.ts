import { ActivityDataBodyInstruction } from "./activity-data-body-instruction";
import { ActivityDataBodyMapping } from "./activity-data-body-mapping";
import { ActivityDataBodyTag } from "./activity-data-body-tag";
import { ActivityDataBodyTheme } from "./activity-data-body-theme";

export class ActivityDataBody {
  instruction?: ActivityDataBodyInstruction;

  mappings?: ActivityDataBodyMapping[];

  theme?: ActivityDataBodyTheme;

  tags?: ActivityDataBodyTag;
}
