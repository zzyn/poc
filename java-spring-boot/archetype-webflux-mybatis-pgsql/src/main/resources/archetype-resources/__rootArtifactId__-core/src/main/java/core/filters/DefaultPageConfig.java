package ${groupId}.core.filters;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

@Configuration
public class DefaultPageConfig {

    @Bean
    @Order(-100)
    public WebFilter defaultPageFilter() {
        return (ServerWebExchange ctx, WebFilterChain chain) -> {
            ServerHttpRequest request = ctx.getRequest();
            String path = request.getPath().value();
            if ("/".equals(path)) {

                return chain.filter(ctx
                        .mutate()
                        .request(request
                                .mutate()
                                .path("/index.html")
                                .build()
                        ).build()
                );
            }

            return chain.filter(ctx);
        };
    }
}
