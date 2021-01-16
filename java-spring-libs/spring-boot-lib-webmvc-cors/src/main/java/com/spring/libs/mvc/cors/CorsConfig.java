package com.spring.libs.mvc.cors;

/**
 * Define the config
 */
public class CorsConfig {
    private String allowedHeaders;
    private String allowedMethods;
    private String allowedOrigin;
    private String maxAge;
    private String[] defaultApiRoute;

    public CorsConfig(String allowedHeaders,
                      String allowedMethods,
                      String allowedOrigin,
                      String maxAge,
                      String[] defaultApiRoute) {
        this.allowedHeaders = allowedHeaders;
        this.allowedMethods = allowedMethods;
        this.allowedOrigin = allowedOrigin;
        this.maxAge = maxAge;
        this.defaultApiRoute = defaultApiRoute;
    }

    public String getAllowedHeaders() { return allowedHeaders; }

    public String getAllowedMethods() {
        return allowedMethods;
    }

    public String getAllowedOrigin() {
        return allowedOrigin;
    }

    public String getMaxAge() {
        return maxAge;
    }

    public String[] getDefaultApiRoute() {
        return defaultApiRoute;
    }
}
