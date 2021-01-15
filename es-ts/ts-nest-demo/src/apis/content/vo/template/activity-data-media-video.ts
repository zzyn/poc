import { ActivityDataMedia } from "./activity-data-media";
import { ActivityDataMediaImage } from "./activity-data-media-image";

export class ActivityDataMediaVideo implements ActivityDataMedia {
  id: string;

  url: string;

  size: number;

  sha1: string;

  mimeType: string;

  duration: number;

  language?: string;

  thumbnails?: ActivityDataMediaImage[];
}
