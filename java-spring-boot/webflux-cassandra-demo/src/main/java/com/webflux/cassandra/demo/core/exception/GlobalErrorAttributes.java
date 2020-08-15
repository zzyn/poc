package com.webflux.cassandra.demo.core.exception;

import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    public GlobalErrorAttributes() {
        this(true);
    }

    public GlobalErrorAttributes(boolean includeException){
        super(includeException);
    }

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {

        Map<String, Object> map = super.getErrorAttributes(request, includeStackTrace);

        return map;
    }
}
