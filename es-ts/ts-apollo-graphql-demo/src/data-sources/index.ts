import { ContentRepoApi } from "./content-repo/content-repo-api";
import { GeneralTestApi } from "./general-test/general-test-api";
import { MigrationPTApi } from "./migration-pt-api";
import { StudentInfoApi } from "./student-info-api";

export type DataSources = {
  generalTestApi: GeneralTestApi;
  migrationPTApi: MigrationPTApi;
  studentInfoApi: StudentInfoApi;
  contentRepoApi: ContentRepoApi;
};

export const createDataSource = (): DataSources => ({
  generalTestApi: new GeneralTestApi(),
  migrationPTApi: new MigrationPTApi(),
  studentInfoApi: new StudentInfoApi(),
  contentRepoApi: new ContentRepoApi(),
});
