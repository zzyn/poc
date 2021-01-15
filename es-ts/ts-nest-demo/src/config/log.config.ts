import { LogLevel } from "@nestjs/common";

interface LogConfig {
  enabled: boolean;
  levels: LogLevel[];
}

export { LogConfig };
