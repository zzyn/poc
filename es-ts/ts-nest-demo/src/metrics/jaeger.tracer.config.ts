import { TracingConfig, TracingOptions } from "jaeger-client";
import { envConfig } from "../yaml.config";

console.log(envConfig.jaeger);

const jaegerTracingConfig: TracingConfig = {
  serviceName: envConfig.jaeger.serviceName,
  sampler: {
    type: "ratelimiting",
    param: 1000,
  },
  reporter: {
    logSpans: envConfig.jaeger.logSpans,
    agentHost: envConfig.jaeger.agentHost,
    agentPort: envConfig.jaeger.agentPort,
  },
};

const jaegerTracingOptions: TracingOptions = {
  tags: envConfig.jaeger.serviceTags,
  logger: {
    info(msg): void {
      console.log("INFO", msg);
    },
    error(msg): void {
      console.log("ERROR", msg);
    },
  },
};

export { jaegerTracingConfig, jaegerTracingOptions };
