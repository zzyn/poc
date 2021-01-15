import { ActivityDataMedia } from "./activity-data-media";

export class ActivityDataMediaAudio implements ActivityDataMedia {
  id: string;

  url: string;

  size: number;

  sha1: string;

  mimeType: string;

  duration: number;
}
