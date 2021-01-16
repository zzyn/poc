package ${groupId}.core.filters;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import static ${groupId}.core.CoreConstants.DEFAULT_API_ROUTE;

@Configuration
public class CorsConfig {

    private static final String ALLOWED_HEADERS = "Content-Type, Accept, Authorization, If-Match, If-Modified-Since, If-None-Match, If-Unmodified-Since, Accept-Encoding, Accept-Language, Origin, X-EF-TOKEN";
    private static final String ALLOWED_METHODS = "GET, PUT, POST, DELETE, PATCH, OPTIONS";
    private static final String ALLOWED_ORIGIN = "*";
    private static final String MAX_AGE = "3600";

    /**
     * common cors (does not support pre-flighted request)
     * https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS
     * https://developer.mozilla.org/en-US/docs/Glossary/Preflight_request
     * @return WebFilter
     */
    @Bean
    @Order(-2)
    public WebFilter corsFilter(){

        return (ServerWebExchange ctx, WebFilterChain chain) -> {
            ServerHttpRequest request = ctx.getRequest();
            String path = request.getPath().value();

            if(path.startsWith(DEFAULT_API_ROUTE)){

                if (CorsUtils.isCorsRequest(request)) {

                    ServerHttpResponse response = ctx.getResponse();
                    HttpHeaders headers = response.getHeaders();
                    headers.add("Access-Control-Allow-Origin", ALLOWED_ORIGIN);

                    if (CorsUtils.isPreFlightRequest(request)) {

                        headers.add("Access-Control-Allow-Headers",ALLOWED_HEADERS);
                        headers.add("Access-Control-Allow-Methods", ALLOWED_METHODS);
                        headers.add("Access-Control-Max-Age", MAX_AGE);

                        response.setStatusCode(HttpStatus.OK);
                        return Mono.empty();
                    }
                }
            }

            return chain.filter(ctx);
        };
    }
}
