package com.webflux.cassandra.demo.core.bind;

import com.webflux.cassandra.demo.core.config.KeyConfig;
import com.webflux.cassandra.demo.core.entity.AccessToken;
import com.webflux.cassandra.demo.core.entity.IdentityToken;
import com.webflux.cassandra.demo.core.entity.JwtContext;
import com.webflux.cassandra.demo.core.jwt.AccessTokenUtils;
import com.webflux.cassandra.demo.core.jwt.IdentityTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static com.webflux.cassandra.demo.core.CoreConstants.ACCESS_TOKEN_HEADER;
import static com.webflux.cassandra.demo.core.CoreConstants.ID_TOKEN_HEADER;


public class JwtContextArgumentResolver implements HandlerMethodArgumentResolver {

    private final ResourceLoader resourceLoader;
    private final KeyConfig keyConfig;

    @Autowired
    public JwtContextArgumentResolver(ResourceLoader resourceLoader, KeyConfig keyConfig) {
        this.resourceLoader = resourceLoader;
        this.keyConfig = keyConfig;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return Objects.nonNull(methodParameter.getParameterAnnotation(Jwt.class));
    }


    @Override
    public Mono<Object> resolveArgument(MethodParameter methodParameter
            , BindingContext bindingContext
            , ServerWebExchange serverWebExchange) {

        JwtContext context = new JwtContext();

        if (methodParameter.getParameterType().isAssignableFrom(JwtContext.class)) {

            HttpHeaders headers = serverWebExchange
                    .getRequest()
                    .getHeaders();

            String identityToken = headers.getFirst(ID_TOKEN_HEADER);
            String accessToken = headers.getFirst(ACCESS_TOKEN_HEADER);

            if (Objects.nonNull(identityToken) || Objects.nonNull(accessToken)) {

                if (Objects.nonNull(identityToken)) {

                    try {
                        IdentityToken idToken = IdentityTokenUtils.getIdentityToken(identityToken, resourceLoader, keyConfig);
                        context.setIdentityToken(idToken);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                if (Objects.nonNull(accessToken)) {

                    try {
                        AccessToken aclToken = AccessTokenUtils.getAccessToken(accessToken, resourceLoader, keyConfig);
                        context.setAccessToken(aclToken);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        return Mono.justOrEmpty(context);
    }
}