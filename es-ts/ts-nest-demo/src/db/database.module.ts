import { DynamicModule, Module } from "@nestjs/common";
import { TypeOrmModule } from "@nestjs/typeorm";
import { envConfig } from "../yaml.config";
import {
  ContentMigrationMappingEntity,
  ProgressMigrationHistoryEntity,
} from "./entity";

@Module({})
export class DatabaseModule {
  static enableRoot(enabled: boolean): DynamicModule {
    if (!enabled) {
      return TypeOrmModule.forRoot({
        type: "sqlite",
        database: "./db.sqlite",
        autoLoadEntities: false,
        synchronize: true,
      });
    }

    return TypeOrmModule.forRoot({
      type: "mysql",
      host: envConfig.db.host,
      port: envConfig.db.port,
      username: envConfig.db.username,
      password: envConfig.db.password,
      database: envConfig.db.database,
      entities: [ContentMigrationMappingEntity, ProgressMigrationHistoryEntity],
      autoLoadEntities: true,
      synchronize: false,
    });
  }
}
