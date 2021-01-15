export interface PTContentRef {
  contentId: string;
  contentRevision: string;
  schemaVersion: number;
}

export interface PTMetaData {
  title: string;
  duration: number;
  contentPath: string;
  contentRef: PTContentRef;
}
