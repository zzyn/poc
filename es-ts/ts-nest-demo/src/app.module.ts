import { HttpModule, Module } from "@nestjs/common";
import { ConfigModule } from "@nestjs/config";
import { ServeStaticModule } from "@nestjs/serve-static";
import { join } from "path";
import { ApiModule } from "./apis/api.module";
import { DatabaseModule } from "./db/database.module";
import { envConfig } from "./yaml.config";

@Module({
  imports: [
    ConfigModule.forRoot({
      isGlobal: true,
      ignoreEnvFile: true,
      expandVariables: true,
      load: [() => envConfig],
      cache: false,
    }),
    ServeStaticModule.forRoot({
      rootPath: join(__dirname, "public"),
    }),
    HttpModule.register({
      timeout: envConfig.http.timeout,
      maxRedirects: envConfig.http.maxRedirects,
    }),
    DatabaseModule.enableRoot(envConfig.db.enabled),
    ApiModule,
  ],
})
export class AppModule {}
