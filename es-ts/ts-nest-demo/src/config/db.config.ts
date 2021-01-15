interface DbConfig {
  enabled: boolean;
  host: string;
  port: number;
  database: string;
  username: string;
  password: string;
  type: string;
}

export { DbConfig };
