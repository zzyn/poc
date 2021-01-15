/* eslint-disable no-console */
import { RedisCache } from "apollo-server-cache-redis";
import { KeyValueCache, PersistedQueryOptions } from "apollo-server-core";
import { ApolloServer, GraphQLExtension } from "apollo-server-express";
import express, { Express } from "express";
import { express as voyagerMiddleware } from "graphql-voyager/middleware";
import path from "path";
import "reflect-metadata";
import serveStatic from "serve-static";
import { corsConfig } from "./config/cors-config";
import { getEnvConfig } from "./config/env";
import { createDataSource } from "./data-sources";
import { contextManager } from "./graphql/context-manager";
import { LogPlugin } from "./metrics/plugins/log-plugin";
import { opentracingExtension } from "./metrics/tracer";
import { createSchema } from "./resolvers";

const {
  playgroundToggle,
  voyager,
  home,
  redisCache,
  jaeger,
  contextPath,
  endpoint,
  health,
  id,
} = getEnvConfig();

console.log(`environment config: ${id}`);

export const createApp = async (): Promise<Express> => {
  const app = express();
  try {
    // Mount Home Page
    if (home.enabled) {
      app.use(
        contextPath || "/",
        serveStatic(path.join(__dirname, home.staticPath), home.options),
      );
    }

    // Health Check
    app.get(
      `${contextPath}${health}`,
      (req: express.Request, res: express.Response) => {
        console.log(req.path);
        res.send("OK");
      },
    );

    // Mount Voyager GraphQL Graph
    if (voyager.enabled) {
      app.use(
        `${contextPath}${voyager.routeUrl}`,
        voyagerMiddleware({
          endpointUrl: `${contextPath}${voyager.endpointUrl}`,
        }),
      );
    }

    let kvCache: KeyValueCache | undefined;
    let persistedQueryOptions: PersistedQueryOptions | false = false;
    if (redisCache.enabled) {
      persistedQueryOptions = {};
      persistedQueryOptions.ttl = redisCache.ttl;
      kvCache = new RedisCache({
        host: redisCache.host,
        port: redisCache.port,
        password: redisCache.password,
        keyPrefix: redisCache.prefix,
        enableReadyCheck: false,
        db: 15,
      });
      persistedQueryOptions.cache = kvCache;
    }

    const customExtensions: Array<() => GraphQLExtension> = [];
    if (jaeger.enabled) {
      customExtensions.push(opentracingExtension);
    }

    const apolloServer = new ApolloServer({
      schema: await createSchema(),
      dataSources: createDataSource,
      context: contextManager,
      playground: playgroundToggle,
      plugins: [LogPlugin],
      cache: kvCache,
      persistedQueries: persistedQueryOptions,
      extensions: customExtensions,
    });

    apolloServer.applyMiddleware({
      app,
      cors: corsConfig,
      path: `${contextPath}${endpoint}`,
      bodyParserConfig: {
        limit: "4MB",
      },
    });

    return app;
  } catch (error) {
    // used for showing the details of 'generating schema error'
    console.error(error);
    throw error;
  }
};
