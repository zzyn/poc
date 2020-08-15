package com.webflux.cassandra.demo.core.bind;

import com.datastax.driver.core.ConsistencyLevel;
import com.webflux.cassandra.demo.core.entity.ConsistencyContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static com.webflux.cassandra.demo.core.CoreConstants.CONSISTENT_LEVEL_HEADER;


public class ConsistencyContextArgumentResolver implements HandlerMethodArgumentResolver {


    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return Objects.nonNull(methodParameter.getParameterAnnotation(Consistency.class));
    }

    @Override
    public Mono<Object> resolveArgument(MethodParameter methodParameter, BindingContext bindingContext, ServerWebExchange serverWebExchange) {

        ConsistencyContext context = new ConsistencyContext();

        if (methodParameter.getParameterType().isAssignableFrom(ConsistencyContext.class)) {

            HttpHeaders headers = serverWebExchange
                    .getRequest()
                    .getHeaders();

            String consistencyLevel = headers.getFirst(CONSISTENT_LEVEL_HEADER);

            if (Objects.nonNull(consistencyLevel) && StringUtils.isNoneEmpty(consistencyLevel)) {

                context.setConsistencyLevel(ConsistencyLevel.valueOf(consistencyLevel.toUpperCase()));
            }
        }

        return Mono.justOrEmpty(context);
    }
}
