import { PTActivityContentRepoResponse } from "./pt-content-repo-acitivity-response";
import { PTTestResponse } from "./pt-content-repo-test-response";

export interface GetContentDetailsRequest {
  contentId: string;
  contentRevision: string;
  schemaVersion: number;
}

export type GetContentDetailsResponse =
  | PTTestResponse
  | PTActivityContentRepoResponse;
