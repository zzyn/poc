/* eslint-disable max-lines */
import dotenv from "dotenv";
import { EnvConfig, EnvKey } from "./env-config";

const envConfigs: Record<EnvKey, EnvConfig> = {
  development: {
    id: "development",
    port: 4000,
    host: "0.0.0.0",
    protocol: "http",
    contextPath: "",
    health: "/health",
    endpoint: "/graphql",
    contentMapUrl: "https://internal-ktsvc-qa.english1.cn/content-map/",
    contentRepoUrl: "https://internal-ktsvc-qa.english1.cn/content-repo/",
    studentInfoUrl: "https://internal-ktsvc-qa.english1.cn/student-info/",
    migrationPTUrl: "https://internal-ktsvc-qa.english1.cn/kmp/",
    generalTestUrl: "https://internal-ktsvc-qa.english1.cn/kgt/",
    generalTestToken:
      "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIwY2IwZjZjMy0xNGU4LTQ0MWQtODZjZS0yZDA5M2JlNjk0YjQiLCJqdGkiOiIyNDU2MDkxYi0zYjE3LTRkNzgtOGJiYi01YWY2MjE1MTViNmQiLCJuYW1lIjoia3QtZ2VuZXJhbC10ZXN0LXN0cmVhbSIsImlhdCI6MTU2NTg5MDUxMywiZXhwIjoyNTY1ODkwNTEzfQ.ZTrfVxMCSupu4wUX5z_Pgk1bU_ZERSVp22S1CyymT-tQikHVTEtChKNzIG5bvjUsJl1er-X_aFgeNMdYIOr8b1w9c1WJpEq12nj7qEBdlrVxsJstt1UCtPRkq3p9mpj2b1axgRFz5MVblfdwWBu-XV8Tf5C9-1173VqcsBnN2mn1TY0VbwofMr388d5TMECvGdkB8kqE4sLYU4QTtafu0XEjZsLC5Oez6LHKGQWuZ_I81GeXoyX05EIq9eo8XLY9viPPDvyXQAO0_A-036rO_vJMPKryGUXjL681-r9Zgxh55BePiMUl-_ZkstDNkUUj_FuTSyUnfXRjIqsT3eK0Pg",
    voyager: {
      enabled: true,
      routeUrl: "/voyager",
      endpointUrl: "/graphql",
    },
    playgroundToggle: true,
    home: {
      enabled: true,
      routeUrl: "/",
      staticPath: "public",
      options: { index: ["index.html"] },
    },
    redisCache: {
      enabled: false,
      host: "cne1qapdredis1.nsmena.ng.0001.cnn1.cache.amazonaws.com.cn",
      port: 6379,
      prefix: "kt:staff:bff:dev:",
      password: "",
      ttl: 300,
      contentTtl: 300,
    },
    jaeger: {
      enabled:
        process.env.JAEGER_ENABLED === undefined
          ? false
          : process.env.JAEGER_ENABLED === "true",
      agentHost:
        process.env.JAEGER_AGENT_HOST === undefined
          ? "localhost"
          : process.env.JAEGER_AGENT_HOST,
      agentPort:
        process.env.JAEGER_AGENT_PORT === undefined
          ? 6832
          : Number(process.env.JAEGER_AGENT_PORT),
      serviceName: "kt-staff-bff-dev",
      // do NOT use DOT(.) in any fields in service tags(https://jira.eflabs.cn/browse/GL-1539)
      serviceTags: { version: "1.0.0" },
    },
    authentication: false,
  },
  test: {
    id: "test",
    port: 4000,
    host: "0.0.0.0",
    protocol: "http",
    contextPath: "",
    health: "/health",
    endpoint: "/graphql",
    contentMapUrl: "https://internal-ktsvc-qa.english1.cn/content-map/",
    contentRepoUrl: "https://internal-ktsvc-qa.english1.cn/content-repo/",
    studentInfoUrl: "https://internal-ktsvc-qa.english1.cn/student-info/",
    migrationPTUrl: "https://internal-ktsvc-qa.english1.cn/kmp/",
    generalTestUrl: "https://internal-ktsvc-qa.english1.cn/kgt/",
    generalTestToken:
      "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIwY2IwZjZjMy0xNGU4LTQ0MWQtODZjZS0yZDA5M2JlNjk0YjQiLCJqdGkiOiIyNDU2MDkxYi0zYjE3LTRkNzgtOGJiYi01YWY2MjE1MTViNmQiLCJuYW1lIjoia3QtZ2VuZXJhbC10ZXN0LXN0cmVhbSIsImlhdCI6MTU2NTg5MDUxMywiZXhwIjoyNTY1ODkwNTEzfQ.ZTrfVxMCSupu4wUX5z_Pgk1bU_ZERSVp22S1CyymT-tQikHVTEtChKNzIG5bvjUsJl1er-X_aFgeNMdYIOr8b1w9c1WJpEq12nj7qEBdlrVxsJstt1UCtPRkq3p9mpj2b1axgRFz5MVblfdwWBu-XV8Tf5C9-1173VqcsBnN2mn1TY0VbwofMr388d5TMECvGdkB8kqE4sLYU4QTtafu0XEjZsLC5Oez6LHKGQWuZ_I81GeXoyX05EIq9eo8XLY9viPPDvyXQAO0_A-036rO_vJMPKryGUXjL681-r9Zgxh55BePiMUl-_ZkstDNkUUj_FuTSyUnfXRjIqsT3eK0Pg",

    voyager: {
      enabled: true,
      routeUrl: "/voyager",
      endpointUrl: "/graphql",
    },
    playgroundToggle: true,
    home: {
      enabled: true,
      routeUrl: "/",
      staticPath: "public",
      options: { index: ["index.html"] },
    },
    redisCache: {
      enabled: false,
      host: "localhost",
      port: 6379,
      prefix: "kt:staff:bff:test:",
      password: "",
      ttl: 300,
      contentTtl: 300,
    },
    jaeger: {
      enabled: true,
      agentHost: "localhost",
      agentPort: 6831,
      serviceName: "kt-staff-bff-test",
      serviceTags: { version: "1.0.0" },
    },
    authentication: false,
  },
  generic: {
    id: process.env.RUNTIME_NODE_ENV as string,
    port: 4000,
    host: "0.0.0.0",
    protocol: "http",
    contextPath: "/ksb",
    health: "/health",
    endpoint: "/graphql",
    contentMapUrl: process.env.CONTENT_MAP_URL as string,
    contentRepoUrl: process.env.CONTENT_REPO_URL as string,
    studentInfoUrl: process.env.STUDENT_INFO_URL as string,
    migrationPTUrl: process.env.MIGRATION_PT_URL as string,
    generalTestUrl: process.env.GENERAL_TEST_URL as string,
    generalTestToken: process.env.GENERAL_TEST_TOKEN as string,
    voyager: {
      enabled: true,
      routeUrl: "/voyager",
      endpointUrl: "/graphql",
    },
    playgroundToggle: true,
    home: {
      enabled: true,
      routeUrl: "/",
      staticPath: "public",
      options: { index: ["index.html"] },
    },
    redisCache: {
      enabled: true,
      host: process.env.REDIS_HOST as string,
      port: Number(process.env.REDIS_PORT as string),
      prefix: process.env.REDIS_PREFIX as string,
      password: "",
      ttl: 300,
      contentTtl: Number(process.env.REDIS_CONTENT_TTL as string),
    },
    jaeger: {
      enabled: (process.env.JAEGER_ENABLED as string) === "true",
      agentHost: process.env.JAEGER_AGENT_HOST as string,
      agentPort: Number(process.env.JAEGER_AGENT_PORT as string),
      serviceName: process.env.JAEGER_SERVICE_NAME as string,
      serviceTags: { version: "1.0.0" },
    },
    authentication: false,
  },
};

const envKeys = Object.keys(envConfigs);
const validEnv = ["qa", "staging", "production"];

export const getEnvConfig = (envKey?: EnvKey): EnvConfig => {
  if (
    process.env.RUNTIME_NODE_ENV &&
    validEnv.find((element) => element === process.env.RUNTIME_NODE_ENV)
  ) {
    return envConfigs.generic;
  }

  const nodeEnv = envKey || dotenv.config().parsed?.env;

  if (!nodeEnv || !envKeys.includes(nodeEnv)) {
    // eslint-disable-next-line no-console
    console.info(
      `Found invalid env variable: ${nodeEnv}. Therefore, running as in 'development'.`,
    );
    return envConfigs.development;
  }

  return envConfigs[nodeEnv as EnvKey];
};
