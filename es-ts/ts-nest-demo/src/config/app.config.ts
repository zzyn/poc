interface AppContextConfig {
  enabled: boolean;
  path: string;
}

interface AppConfig {
  name: string | undefined;
  port: number;
  context: AppContextConfig;
}

export { AppConfig, AppContextConfig };
