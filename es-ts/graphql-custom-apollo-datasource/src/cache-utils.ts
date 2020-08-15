import { createHash } from "crypto";
import { Body } from "./kt-rest-data-source";

export function createDefaultCacheKey(url: string, body?: Body): string {
  const hash = createHash("sha256");

  if (body) {
    return `${url}:${hash.update(JSON.stringify(body)).digest("hex")}`;
  }

  return url;
}
