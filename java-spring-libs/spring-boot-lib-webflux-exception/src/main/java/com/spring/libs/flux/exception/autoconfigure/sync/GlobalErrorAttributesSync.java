package com.spring.libs.flux.exception.autoconfigure.sync;

import com.spring.libs.flux.exception.exceptions.ApiException;
import com.spring.libs.flux.exception.exceptions.InvalidRequestException;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class GlobalErrorAttributesSync extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        Throwable errorCause = getError(webRequest);
        if (errorCause instanceof ApiException) {
            errorAttributes.put("subStatus", ((ApiException)errorCause).getErrorCode());

            if(errorCause instanceof InvalidRequestException) {
                errorAttributes.put("errors", ((InvalidRequestException)errorCause).getErrors());
            }
        }
        return errorAttributes;
    }
}
