import { ActivityDataStimulusTag } from "./activity-data-stimulus-tag";

export class ActivityDataStimulusBodyTag {
  compassTags?: string[];

  secondaryCompassTags?: string[];

  text?: ActivityDataStimulusTag;

  image?: ActivityDataStimulusTag;

  audio?: ActivityDataStimulusTag;

  video?: ActivityDataStimulusTag;
}
