interface RedisConfig {
  enabled: boolean;
  host: string;
  port: number;
  ttl: number;
  contentTtl: number;
  password: string;
  prefix: string;
}

export { RedisConfig };
