interface JaegerConfig {
  enabled: boolean | undefined;
  agentHost: string | undefined;
  agentPort: number | undefined;
  logSpans: boolean | undefined;
  serviceName: string | undefined;
  serviceTags: any;
}

export { JaegerConfig };
