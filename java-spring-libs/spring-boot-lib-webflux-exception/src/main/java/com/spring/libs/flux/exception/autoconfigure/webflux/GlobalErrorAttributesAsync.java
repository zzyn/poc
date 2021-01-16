package com.spring.libs.flux.exception.autoconfigure.webflux;

import com.spring.libs.flux.exception.exceptions.ApiException;
import com.spring.libs.flux.exception.exceptions.InvalidRequestException;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

@Component
public class GlobalErrorAttributesAsync extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(request, includeStackTrace);
        Throwable error = getError(request);
        Throwable errorCause = error.getCause();
        if (errorCause instanceof ApiException) {
            errorAttributes.put("subStatus", ((ApiException)errorCause).getErrorCode());

            if(errorCause instanceof InvalidRequestException) {
                errorAttributes.put("errors", ((InvalidRequestException)errorCause).getErrors());
            }
        }
        return errorAttributes;
    }
}
