import { ActivityDataMedia } from "./activity-data-media";

export class ActivityDataMediaImage implements ActivityDataMedia {
  id: string;

  url: string;

  size: number;

  sha1: string;

  mimeType: string;

  width: number;

  height: number;
}
