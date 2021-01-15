import { ActivityDataMediaAudio } from "./activity-data-media-audio";
import { ActivityDataMediaImage } from "./activity-data-media-image";
import { ActivityDataQuestionBodyOptionTextsItem } from "./activity-data-question-body-option-texts-item";

export class ActivityDataQuestionBodyOption {
  id: string;

  text?: string;

  audio?: ActivityDataMediaAudio;

  image?: ActivityDataMediaImage;

  type?: string;

  texts?: ActivityDataQuestionBodyOptionTextsItem[];

  images?: ActivityDataMediaImage[];

  rowIndex?: number;

  colIndex?: number;
}
