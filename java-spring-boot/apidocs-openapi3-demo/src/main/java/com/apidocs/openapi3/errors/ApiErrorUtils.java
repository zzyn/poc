package com.apidocs.openapi3.errors;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

@Slf4j
public final class ApiErrorUtils {

    public static void setErrorResponse(HttpServletRequest request, HttpServletResponse response, ApiException ex, Boolean includeStackTrace) {

        response.setStatus(ex.getStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ApiErrorDto apiErrorDto = getApiError(request, ex, includeStackTrace);

        try {
            ObjectMapper mapper = new ObjectMapper();
            String msg = mapper.writeValueAsString(apiErrorDto);
            response.getWriter().write(msg);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    public static ApiErrorDto getApiError(HttpServletRequest request, ApiException ex, boolean includeStackTrace){

        String trace = null;

        if(includeStackTrace){
            StringWriter stackTrace = new StringWriter();
            ex.printStackTrace(new PrintWriter(stackTrace));
            stackTrace.flush();
            trace = stackTrace.toString();
        }

        log.error(ex.getMessage(), ex);

        ApiErrorDto apiErrorDto = new ApiErrorDto()
                .setStatus(ex.getStatus().value())
                .setError(ex.getStatus().name())
                .setMessage(ex.getMessage())
                .setCode(ex.getReason())
                .setPath(request.getServletPath())
                .setTimestamp(System.currentTimeMillis())
                .setException(ex.toString())
                .setTrace(trace);

        return apiErrorDto;
    }
}

