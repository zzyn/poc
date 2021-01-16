package ${groupId}.app.security;

import ${groupId}.core.config.KeyConfig;
import ${groupId}.core.exception.ApiException;
import ${groupId}.core.security.IdentityTokenUtils;
import ${groupId}.core.security.KeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.io.InputStream;
import java.security.PublicKey;
import java.util.Objects;

import static ${groupId}.core.CoreConstants.DEFAULT_API_ROUTE;
import static ${groupId}.core.CoreConstants.ID_TOKEN_HEADER;

@Configuration
public class SecurityFilter {

    private final KeyConfig keyConfig;
    private final ResourceLoader resourceLoader;

    @Autowired
    public SecurityFilter(KeyConfig keyConfig, ResourceLoader resourceLoader) {
        this.keyConfig = keyConfig;
        this.resourceLoader = resourceLoader;
    }

    @Bean
    @Order(-1)
    public WebFilter appSecurityFilter() {

        return (ServerWebExchange ctx, WebFilterChain chain) -> {
            ServerHttpRequest request = ctx.getRequest();
            HttpHeaders headers = request.getHeaders();
            String path = request.getPath().value();

            if (path.startsWith(DEFAULT_API_ROUTE)) {

                if (!headers.containsKey(ID_TOKEN_HEADER)) {
                    return Mono.error(new ApiException(HttpStatus.BAD_REQUEST, String.format("header : %s is requierd", ID_TOKEN_HEADER)));
                }

                String jwt = headers.getFirst(ID_TOKEN_HEADER);

                if (Objects.isNull(jwt) || jwt.isEmpty()) {
                    return Mono.error(new ApiException(HttpStatus.BAD_REQUEST, String.format("header : %s is empty", ID_TOKEN_HEADER)));
                }

                PublicKey publicKey = null;
                try {
                    InputStream inputStream = resourceLoader.getClassLoader().getResourceAsStream(keyConfig.getPublicKey());
                    publicKey = KeyUtils.getPublicKey(inputStream);
                } catch (Exception e) {
                    return Mono.error(new ApiException(HttpStatus.UNAUTHORIZED, "invalid ssl key"));
                }

                Boolean isValidToken;
                try {
                    isValidToken = IdentityTokenUtils.verifyToken(jwt, publicKey);
                } catch (Exception e) {
                    return Mono.error(new ApiException(HttpStatus.UNAUTHORIZED, e.getMessage()));
                }

                if (!isValidToken) {
                    return Mono.error(new ApiException(HttpStatus.UNAUTHORIZED, "invalid token"));
                }
            }
            return chain.filter(ctx);
        };
    }
}
