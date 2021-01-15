import { v1 as uuidv1 } from "uuid";

export function generateContentId(): string {
  return uuidv1();
}
