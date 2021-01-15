export interface PTTestActivityRef {
  contentId: string;
  contentRevision: string;
  schemaVersion: number;
  activitySequence: number;
}

export interface PTTestSkill {
  code: string;
  activityRefs: PTTestActivityRef[];
}

export interface PTTestData {
  title: string;
  duration: number;
  skills: PTTestSkill[];
}

export interface PTTestResponse {
  schemaVersion: number;
  contentRevision: string;
  contentId: string;
  data: PTTestData;
  domainType: string;
  entityType: string;
  id: string;
}
