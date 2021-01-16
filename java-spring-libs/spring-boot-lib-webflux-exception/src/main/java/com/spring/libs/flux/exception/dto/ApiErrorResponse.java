package com.spring.libs.flux.exception.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.libs.flux.exception.exceptions.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiErrorResponse {

    private String path;
    private int status;
    private int subStatus;
    private String error;
    private String message;
    private List<Object> errors = new ArrayList();

    public ApiException toApiException() {

        if (this.getStatus() == 400) {
            return new InvalidRequestException(this.getSubStatus(), this.getMessage(), this.getErrors());
        }

        if (this.getStatus() == 401) {
            return new UnauthenticatedException(this.getSubStatus(), this.getMessage());
        }

        if (this.getStatus() == 403) {
            return new UnauthorizedException(this.getSubStatus(), this.getMessage());
        }

        if (this.getStatus() == 404) {
            return new ResourceNotFoundException(this.getSubStatus(), this.getMessage());
        }

        if (this.getStatus() == 409) {
            return new BusinessConflictException(this.getSubStatus(), this.getMessage());
        }

        return new ApiException(this.toString());
    }
}
