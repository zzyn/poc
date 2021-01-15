import { CorsOptions } from "cors";

const corsMethods: string[] = [
  "GET",
  "POST",
  "PUT",
  "DELETE",
  "PATCH",
  "OPTIONS",
];

const corsHeaders: string[] = [
  "Content-Type",
  "Accept",
  "Authorization",
  "If-Match",
  "If-Modified-Since",
  "If-None-Match",
  "If-Unmodified-Since",
  "Accept-Encoding",
  "Accept-Language",
  "Origin",
  "X-EF-ID",
  "X-EF-ACCESS",
];
const corsConfig: CorsOptions = {
  origin: "*",
  methods: corsMethods,
  allowedHeaders: corsHeaders,
  exposedHeaders: corsHeaders,
  credentials: true,
  maxAge: 3600,
  preflightContinue: false,
  optionsSuccessStatus: 200,
};

export { corsConfig };
