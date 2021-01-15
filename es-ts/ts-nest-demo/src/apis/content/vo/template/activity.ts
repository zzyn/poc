import { ActivityData } from "./activity-data";
import { ActivityMeta } from "./activity-meta";

export class Activity {
  id: string;

  meta?: ActivityMeta;

  data?: ActivityData;
}
