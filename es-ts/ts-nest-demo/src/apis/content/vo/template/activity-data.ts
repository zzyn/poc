import { ActivityDataBody } from "./activity-data-body";
import { ActivityDataQuestion } from "./activity-data-question";
import { ActivityDataStimulus } from "./activity-data-stimulus";

export class ActivityData {
  Key: string;

  Title: string;

  Type: string;

  Body: ActivityDataBody;

  Stimulus?: ActivityDataStimulus[];

  Questions: ActivityDataQuestion[];
}
