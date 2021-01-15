import { LogLevel } from "@nestjs/common";
import { existsSync, readFileSync } from "fs";
import * as yaml from "js-yaml";
import { join } from "path";
import {
  AppConfig,
  AppContextConfig,
  DbConfig,
  EnvConfig,
  HttpConfig,
  JaegerConfig,
  LogConfig,
  SdkConfig,
  SwaggerConfig,
} from "./config";
import { ContentRepoSdkConfig } from "./sdks/content-repo/config";
import { GeneralTestSdkConfig } from "./sdks/general-test/config";
import { OspSdkConfig } from "./sdks/osp/config";

const YAML_CONFIG_PATH = "./yaml/";
let YAML_CONFIG_FILENAME = `${YAML_CONFIG_PATH}test.yml`;
if (process.env.RUNTIME_NODE_ENV === undefined) {
  YAML_CONFIG_FILENAME = `${YAML_CONFIG_PATH}dev.yml`;
} else {
  YAML_CONFIG_FILENAME = `${YAML_CONFIG_PATH}${process.env.RUNTIME_NODE_ENV}.yml`;
}

const envAny: any = () => {
  if (existsSync(join(YAML_CONFIG_FILENAME))) {
    return yaml.load(readFileSync(join(YAML_CONFIG_FILENAME), "utf8"));
  }
  return {};
};

const envConfig: EnvConfig = envAny() as EnvConfig;

//  handler env config

if (envConfig.id === undefined) {
  if (process.env.RUNTIME_NODE_ENV === undefined) {
    envConfig.id = "dev";
  } else {
    envConfig.id = `${process.env.RUNTIME_NODE_ENV}`;
  }
}

//  handler app env
if (envConfig.app === undefined) {
  envConfig.app = {} as AppConfig;
}

if (envConfig.app.name === undefined) {
  if (process.env.APP_NAME === undefined) {
    envConfig.app.name = `kt-migration-pt[${envConfig.id}]`;
  } else {
    envConfig.app.name = process.env.APP_NAME;
  }
}

if (envConfig.app.port === undefined) {
  if (process.env.APP_PORT === undefined) {
    envConfig.app.port = 4000;
  } else {
    envConfig.app.port = Number(process.env.APP_PORT);
  }
}

if (envConfig.app.context === undefined) {
  envConfig.app.context = {} as AppContextConfig;
}

if (envConfig.app.context.enabled === undefined) {
  envConfig.app.context.enabled = process.env.APP_CONTEXT_ENABLED === "true";
}

if (envConfig.app.context.path === undefined) {
  if (process.env.APP_CONTEXT_PATH === undefined) {
    envConfig.app.context.path = "";
  } else {
    envConfig.app.context.path = process.env.APP_CONTEXT_PATH;
  }
}

//  handler db env
if (envConfig.db === undefined) {
  envConfig.db = {} as DbConfig;
}

if (envConfig.db.enabled === undefined) {
  envConfig.db.enabled = process.env.DB_ENABLED === "true";
}

if (envConfig.db.host === undefined) {
  if (process.env.DB_HOST === undefined) {
    envConfig.db.host = "localhost";
  } else {
    envConfig.db.host = process.env.DB_HOST;
  }
}

if (envConfig.db.database === undefined) {
  if (process.env.DB_DATABASE === undefined) {
    envConfig.db.database = "";
  } else {
    envConfig.db.database = process.env.DB_DATABASE;
  }
}

if (envConfig.db.port === undefined) {
  if (process.env.DB_PORT === undefined) {
    envConfig.db.port = 3306;
  } else {
    envConfig.db.port = Number(process.env.DB_PORT);
  }
}

if (envConfig.db.username === undefined) {
  if (process.env.DB_USERNAME === undefined) {
    envConfig.db.username = "";
  } else {
    envConfig.db.username = process.env.DB_USERNAME;
  }
}

if (envConfig.db.password === undefined) {
  if (process.env.DB_PASSWORD === undefined) {
    envConfig.db.password = "";
  } else {
    envConfig.db.password = process.env.DB_PASSWORD;
  }
}

if (envConfig.db.type === undefined) {
  if (process.env.DB_TYPE === undefined) {
    envConfig.db.type = "mysql";
  } else {
    envConfig.db.type = process.env.DB_TYPE;
  }
}

//  handler sdk env
if (envConfig.sdk === undefined) {
  envConfig.sdk = {} as SdkConfig;
}
if (envConfig.sdk.osp === undefined) {
  envConfig.sdk.osp = {} as OspSdkConfig;
}

if (envConfig.sdk["content-repo"] === undefined) {
  envConfig.sdk["content-repo"] = {} as ContentRepoSdkConfig;
}

if (envConfig.sdk["general-test"] === undefined) {
  envConfig.sdk["general-test"] = {} as GeneralTestSdkConfig;
}

if (envConfig.sdk.osp.url === undefined) {
  if (process.env.SDK_OSP_URL === undefined) {
    envConfig.sdk.osp.url = "";
  } else {
    envConfig.sdk.osp.url = process.env.SDK_OSP_URL;
  }
}

if (envConfig.sdk["content-repo"].url === undefined) {
  if (process.env.SDK_CONTENT_REPO_URL === undefined) {
    envConfig.sdk["content-repo"].url = "";
  } else {
    envConfig.sdk["content-repo"].url = process.env.SDK_CONTENT_REPO_URL;
  }
}

if (envConfig.sdk["general-test"].url === undefined) {
  if (process.env.SDK_GENERAL_TEST_URL === undefined) {
    envConfig.sdk["general-test"].url = "";
  } else {
    envConfig.sdk["general-test"].url = process.env.SDK_GENERAL_TEST_URL;
  }
}

if (envConfig.sdk["general-test"].idToken === undefined) {
  if (process.env.SDK_GENERAL_TEST_ID_TOKEN === undefined) {
    envConfig.sdk["general-test"].idToken = "";
  } else {
    envConfig.sdk["general-test"].idToken =
      process.env.SDK_GENERAL_TEST_ID_TOKEN;
  }
}

if (envConfig.sdk["general-test"].accessToken === undefined) {
  if (process.env.SDK_GENERAL_TEST_ACCESS_TOKEN === undefined) {
    envConfig.sdk["general-test"].accessToken = "";
  } else {
    envConfig.sdk["general-test"].accessToken =
      process.env.SDK_GENERAL_TEST_ACCESS_TOKEN;
  }
}

//  handler swagger env
if (envConfig.swagger === undefined) {
  envConfig.swagger = {} as SwaggerConfig;
}

if (envConfig.swagger.enabled === undefined) {
  envConfig.swagger.enabled = process.env.SWAGGER_ENABLED === "true";
}

if (envConfig.swagger.path === undefined) {
  if (process.env.SWAGGER_PATH === undefined) {
    envConfig.swagger.path = "";
  } else {
    envConfig.swagger.path = process.env.SWAGGER_PATH;
  }
}

if (envConfig.swagger.title === undefined) {
  if (process.env.SWAGGER_TITLE === undefined) {
    envConfig.swagger.title = "";
  } else {
    envConfig.swagger.title = process.env.SWAGGER_TITLE;
  }
}

if (envConfig.swagger.version === undefined) {
  if (process.env.SWAGGER_VERSION === undefined) {
    envConfig.swagger.version = "";
  } else {
    envConfig.swagger.version = process.env.SWAGGER_VERSION;
  }
}

if (envConfig.swagger.description === undefined) {
  if (process.env.SWAGGER_DESCRIPTION === undefined) {
    envConfig.swagger.description = "";
  } else {
    envConfig.swagger.description = process.env.SWAGGER_DESCRIPTION;
  }
}

//  handler log env
if (envConfig.log === undefined) {
  envConfig.log = {} as LogConfig;
}

if (envConfig.log.enabled === undefined) {
  envConfig.log.enabled = process.env.LOG_ENABLED === "true";
}

if (envConfig.log.levels === undefined) {
  if (process.env.LOG_LEVELS === undefined) {
    envConfig.log.levels = ["log", "error", "warn"];
  } else {
    envConfig.log.levels = process.env.LOG_LEVELS.split(",").map(
      (x) => x as LogLevel,
    );
  }
}

//  handler http env
if (envConfig.http === undefined) {
  envConfig.http = {} as HttpConfig;
}

if (envConfig.http.maxRedirects === undefined) {
  if (process.env.HTTP_MAX_REDIRECTS === undefined) {
    envConfig.http.maxRedirects = 5;
  } else {
    envConfig.http.maxRedirects = Number(process.env.HTTP_MAX_REDIRECTS);
  }
}

if (envConfig.http.timeout === undefined) {
  if (process.env.HTTP_TIMEOUT === undefined) {
    envConfig.http.timeout = 5000;
  } else {
    envConfig.http.timeout = Number(process.env.HTTP_TIMEOUT);
  }
}

//  handler jaeger env

if (envConfig.jaeger === undefined) {
  envConfig.jaeger = {} as JaegerConfig;
}

if (envConfig.jaeger.enabled === undefined) {
  if (process.env.JAEGER_ENABLED === undefined) {
    envConfig.jaeger.enabled = false;
  } else {
    envConfig.jaeger.enabled = process.env.JAEGER_ENABLED === "true";
  }
}

if (envConfig.jaeger.agentHost === undefined) {
  if (process.env.JAEGER_AGENT_HOST === undefined) {
    envConfig.jaeger.agentHost = "localhost";
  } else {
    envConfig.jaeger.agentHost = process.env.JAEGER_AGENT_HOST;
  }
}

if (envConfig.jaeger.agentPort === undefined) {
  if (process.env.JAEGER_AGENT_PORT === undefined) {
    envConfig.jaeger.agentPort = 6831;
  } else {
    envConfig.jaeger.agentPort = Number(process.env.JAEGER_AGENT_PORT);
  }
}

if (envConfig.jaeger.logSpans === undefined) {
  if (process.env.JAEGER_LOG_SPANS_ENABLED === undefined) {
    envConfig.jaeger.logSpans = false;
  } else {
    envConfig.jaeger.logSpans = process.env.JAEGER_LOG_SPANS_ENABLED === "true";
  }
}

if (envConfig.jaeger.serviceTags === undefined) {
  envConfig.jaeger.serviceTags = { version: "1.0.0" };
}

if (envConfig.jaeger.serviceName === undefined) {
  envConfig.jaeger.serviceName = `kt-migration-pt[${envConfig.id}]`;
}

console.warn(envConfig);

export { envConfig };
