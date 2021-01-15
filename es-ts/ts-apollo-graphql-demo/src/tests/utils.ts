// reference: https://github.com/apollographql/fullstack-tutorial/blob/master/final/server/src/__tests__/__utils.js
import { DocumentNode, toPromise } from "apollo-link";
import { InMemoryLRUCache } from "apollo-server-caching";
import { ApolloServer, ApolloServerExpressConfig } from "apollo-server-express";
import { GraphQLResponse } from "apollo-server-types";
import "reflect-metadata";
import { createDataSource } from "../data-sources";
import { createSchema } from "../resolvers";

module.exports.toPromise = toPromise;

interface TestServerConfig {
  context?: ApolloServerExpressConfig;
}

// TODO: add default context (e.g. token) to config
export const createTestServer = async (config?: TestServerConfig) => {
  const dataSources = await createDataSource();
  const inMemoryCache = new InMemoryLRUCache();
  const server = new ApolloServer({
    schema: await createSchema(),
    dataSources: () => dataSources,
    context: config?.context?.context,
    cache: inMemoryCache,
    debug: true,
  });

  return { server, dataSources, inMemoryCache };
};

// --------- types ----------

type Query = {
  query: string | DocumentNode;
  variables?: {
    [name: string]: any;
  };
};

type Mutation = {
  mutation: string | DocumentNode;
  variables?: {
    [name: string]: any;
  };
};

export type MutateFunction = (mutation: Mutation) => Promise<GraphQLResponse>;
export type QueryFunction = (query: Query) => Promise<GraphQLResponse>;
