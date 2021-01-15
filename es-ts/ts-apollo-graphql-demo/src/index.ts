/* eslint-disable no-console */
import { createApp } from "./app";
import { getEnvConfig } from "./config/env";

const { port } = getEnvConfig();

(async () => {
  const app = await createApp();
  app.listen(port, () => {
    console.log(`🚀 server started at http://localhost:${port}/graphql`);
  });
})();
