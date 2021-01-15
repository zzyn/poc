import { ActivityDataMediaAudio } from "./activity-data-media-audio";
import { ActivityDataMediaImage } from "./activity-data-media-image";
import { ActivityDataMediaVideo } from "./activity-data-media-video";

export class ActivityDataQuestionBodyTest {
  text?: string;

  videos?: ActivityDataMediaVideo[];

  image?: ActivityDataMediaImage;

  audio?: ActivityDataMediaAudio;
}
