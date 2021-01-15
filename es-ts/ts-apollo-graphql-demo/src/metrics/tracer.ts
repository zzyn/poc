import OpentracingExtension, {
  ExtendedGraphQLResolveInfo,
} from "apollo-opentracing";
import {
  initTracer,
  JaegerTracer,
  TracingConfig,
  TracingOptions,
} from "jaeger-client";
import * as opentracing from "opentracing";
import { getEnvConfig } from "../config/env";
import { GraphQLCustomResolversContext } from "../graphql/context";

const { jaeger } = getEnvConfig();

/* See schema
 * https://github.com/jaegertracing/jaeger-client-node/blob/master/src/configuration.js#L37
 * See metrics
 * https://github.com/jaegertracing/jaeger-client-node
 * See sampler
 * https://www.jaegertracing.io/docs/1.17/sampling/
 *
 */

const config: TracingConfig = {
  serviceName: jaeger.serviceName,
  sampler: {
    type: "ratelimiting", // https://jira.eflabs.cn/browse/GL-1539
    param: 1000,
  },
  reporter: {
    logSpans:
      process.env.JAEGER_LOG_SPANS_ENABLED === undefined
        ? false
        : process.env.JAEGER_LOG_SPANS_ENABLED === "true",
    agentHost: jaeger.agentHost,
    agentPort: jaeger.agentPort,
  },
};

const options: TracingOptions = {
  tags: jaeger.serviceTags,
  logger: {
    info(msg): void {
      console.log("INFO", msg);
    },
    error(msg): void {
      console.log("ERROR", msg);
    },
  },
};

const tracer: JaegerTracer = initTracer(config, options);
opentracing.initGlobalTracer(tracer);

const opentracingExtension = () => {
  return new OpentracingExtension({
    local: tracer,
    server: tracer,
    shouldTraceFieldResolver: (_src, _args, _context, info) => {
      // Only trace Query instead of each sub field resolver
      if (info.path.prev === undefined) {
        return true;
      }

      return false;
    },
    shouldTraceRequest: () => {
      return true;
    },
    onFieldResolve: (_src, _args, context, info) => {
      // Only trace Query instead of each sub field resolver
      if (info.path.prev === undefined) {
        // eslint-disable-next-line no-param-reassign
        ((context as unknown) as GraphQLCustomResolversContext).rootSpan = (info as ExtendedGraphQLResolveInfo).span;
      }
    },
  });
};

export { opentracingExtension };
