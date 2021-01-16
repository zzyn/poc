package com.spring.libs.flux.exception.utils;

import com.spring.libs.flux.exception.dto.ApiErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpServerErrorException;

public class ResponseEntityConverter {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private ResponseEntityConverter() {
    }

    public static <T> T toResult(ResponseEntity<T> response) {
        if (response.getStatusCode().isError()) {
            throw toException(response);
        }
        return response.getBody();
    }

    private static RuntimeException toException(ResponseEntity responseEntity) {
        String errorBody = null;

        if (HttpErrorChecker.isCustomError(responseEntity.getStatusCode())) {
            ApiErrorResponse errorResponse;
            try {
                errorBody = responseEntity.getBody().toString();
                errorResponse = objectMapper.readValue(errorBody, ApiErrorResponse.class);
                if(StringUtils.isEmpty(errorResponse.getMessage())) {
                    errorResponse.setMessage(errorBody);
                }
            } catch (Exception ex) {
                errorResponse = new ApiErrorResponse();
                errorResponse.setMessage(errorBody);
            }

            errorResponse.setStatus(responseEntity.getStatusCodeValue());
            return errorResponse.toApiException();
        }

        errorBody = responseEntity.hasBody() ? responseEntity.getBody().toString() : "";
        return new HttpServerErrorException(responseEntity.getStatusCode(), errorBody);
    }
}
