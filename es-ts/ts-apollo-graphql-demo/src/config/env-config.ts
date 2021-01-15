import { HomeConfig } from "./home-config";
import { JaegerConfig } from "./jaeger-config";
import { RedisConfig } from "./redis-config";
import { VoyagerConfig } from "./voyager-config";

interface EnvConfig {
  id: string;
  port: number;
  host: string;
  contextPath: string;
  endpoint: string;
  health: string;
  protocol: "http" | "https";
  contentMapUrl: string;
  contentRepoUrl: string;
  migrationPTUrl: string;
  studentInfoUrl: string;
  generalTestUrl: string;
  generalTestToken: string;
  voyager: VoyagerConfig;
  playgroundToggle: boolean;
  authentication: boolean;
  home: HomeConfig;
  redisCache: RedisConfig;
  jaeger: JaegerConfig;
}

type EnvKey = "development" | "test" | "generic";

export { EnvConfig, EnvKey };
