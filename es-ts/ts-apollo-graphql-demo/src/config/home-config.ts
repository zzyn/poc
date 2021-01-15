import { ServeStaticOptions } from "serve-static";

interface HomeConfig {
  enabled: boolean;
  routeUrl: string;
  staticPath: string;
  options: ServeStaticOptions;
}

export { HomeConfig };
