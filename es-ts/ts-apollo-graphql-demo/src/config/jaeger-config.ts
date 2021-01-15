interface JaegerConfig {
  enabled: boolean;
  serviceName: string;
  agentHost: string;
  agentPort: number;
  serviceTags: any;
}

export { JaegerConfig };
