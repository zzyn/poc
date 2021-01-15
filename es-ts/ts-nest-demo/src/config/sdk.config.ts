import { ContentRepoSdkConfig } from "../sdks/content-repo/config";
import { GeneralTestSdkConfig } from "../sdks/general-test/config";
import { OspSdkConfig } from "../sdks/osp/config";

interface SdkConfig {
  osp: OspSdkConfig;
  "content-repo": ContentRepoSdkConfig;
  "general-test": GeneralTestSdkConfig;
}

export { SdkConfig };
