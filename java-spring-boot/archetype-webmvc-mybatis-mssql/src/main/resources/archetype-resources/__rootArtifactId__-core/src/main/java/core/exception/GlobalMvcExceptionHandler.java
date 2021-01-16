package ${groupId}.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalMvcExceptionHandler extends ResponseEntityExceptionHandler {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Value("${server.error.include-stacktrace}")
    private String includeStackTrace;

    private Boolean isIncludeStackTrace(){
        return "always".equals(includeStackTrace);
    }

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> defaultExceptionHandler(HttpServletRequest request, WebRequest webRequest, HttpServletResponse response, Exception e) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiException ae;
        if (e instanceof ApiException) {
            ae = (ApiException) e;
            status = ae.getStatus();
        } else {
            ae = new ApiException(status, null, e);
        }

        ApiError errorModel = ApiErrorUtils.getApiError(request, ae, isIncludeStackTrace());

        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);

        return handleExceptionInternal(ae, errorModel, headers, status, webRequest);
    }
}
