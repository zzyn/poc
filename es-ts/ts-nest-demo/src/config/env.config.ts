import { AppConfig } from "./app.config";
import { DbConfig } from "./db.config";
import { HttpConfig } from "./http.config";
import { JaegerConfig } from "./jaeger.config";
import { LogConfig } from "./log.config";
import { SdkConfig } from "./sdk.config";
import { SwaggerConfig } from "./swagger.config";

interface EnvConfig {
  id: string;
  app: AppConfig;
  swagger: SwaggerConfig;
  db: DbConfig;
  log: LogConfig;
  http: HttpConfig;
  sdk: SdkConfig;
  jaeger: JaegerConfig;
}

export { EnvConfig };
