import { ActivityDataQuestionBodyOption } from "./activity-data-question-body-option";
import { ActivityDataQuestionBodyTag } from "./activity-data-question-body-tag";
import { ActivityDataQuestionBodyTest } from "./activity-data-question-body-test";

export class ActivityDataQuestionBody {
  version: number;

  tags?: ActivityDataQuestionBodyTag[];

  tests: ActivityDataQuestionBodyTest[];

  answers: string[][];

  options: ActivityDataQuestionBodyOption[];

  additionalLength?: number;
}
