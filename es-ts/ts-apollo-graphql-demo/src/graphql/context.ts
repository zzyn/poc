import express from "express";
import { Span } from "opentracing";
import { DataSources } from "../data-sources";
// import { AclItem } from "../data-sources/models/auth2-acl-item";

export interface GraphQLCustomContext {
  req: express.Request;
  res: express.Response;
  user: any;
  access: any;
  // getAcl(): Promise<AclItem[]>;
}

export interface GraphQLCustomResolversContext extends GraphQLCustomContext {
  dataSources: DataSources;
  rootSpan?: Span;
}
