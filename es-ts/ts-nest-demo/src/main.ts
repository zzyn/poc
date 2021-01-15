import { JaegerInterceptor } from "@chankamlam/nest-jaeger";
import { Logger } from "@nestjs/common";
import { NestFactory } from "@nestjs/core";
import { NestExpressApplication } from "@nestjs/platform-express";
import { DocumentBuilder, SwaggerModule } from "@nestjs/swagger";
import { AppModule } from "./app.module";
import { corsConfig } from "./config/cors.config";
import { ApiExceptionFilter } from "./filter";
import { jaegerTracingConfig, jaegerTracingOptions } from "./metrics";
import { envConfig } from "./yaml.config";

const logger = new Logger("NestApplication", true);

async function bootstrap() {
  const app = await NestFactory.create<NestExpressApplication>(AppModule);

  console.log(`RUNTIME_NODE_ENV: ${process.env.RUNTIME_NODE_ENV}`);

  console.log(`env_yaml: ${envConfig.id}`);

  if (envConfig.app.context.enabled) {
    app.setGlobalPrefix(envConfig.app.context.path);
  }

  if (envConfig.jaeger.enabled) {
    app.useGlobalInterceptors(
      new JaegerInterceptor(jaegerTracingConfig, jaegerTracingOptions),
    );
  }

  app.useGlobalFilters(new ApiExceptionFilter());

  if (envConfig.log.enabled) {
    app.useLogger(envConfig.log.levels);
  } else {
    app.useLogger(false);
  }

  app.enableCors(corsConfig);

  if (envConfig.swagger.enabled) {
    const options = new DocumentBuilder()
      .setTitle(envConfig.swagger.title)
      .setDescription(envConfig.swagger.description)
      .setVersion(envConfig.swagger.version)
      .build();
    const document = SwaggerModule.createDocument(app, options);

    let { path } = envConfig.swagger;
    if (envConfig.app.context.enabled) {
      path = `${envConfig.app.context.path}/${path}`;
    }

    SwaggerModule.setup(path, app, document);
  }

  await app.listen(envConfig.app.port);

  const url = await app.getUrl();
  logger.verbose(`Nest application is listening on ${url}`);
}

(async (): Promise<void> => {
  await bootstrap();
})().catch((error: Error) => {
  logger.error(`Nest application error: ${error.message}`);
});
