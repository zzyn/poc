package ${groupId}.core.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public final class ApiErrorUtils {

    private static Logger logger = LoggerFactory.getLogger(ApiErrorUtils.class);

    public static void setErrorResponse(HttpServletRequest request, HttpServletResponse response, ApiException ex, Boolean includeStackTrace) {

        response.setStatus(ex.getStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

        ApiError apiError = getApiError(request, ex, includeStackTrace);

        try {
            ObjectMapper mapper = new ObjectMapper();
            String msg = mapper.writeValueAsString(apiError);
            logger.info(msg);
            response.getWriter().write(msg);
        } catch (IOException e) {
            logger.warn(e.getMessage(), e);
        }
    }

    public static ApiError getApiError(HttpServletRequest request, ApiException ex, boolean includeStackTrace){

        String trace = null;

        if(includeStackTrace){
            StringWriter stackTrace = new StringWriter();
            ex.printStackTrace(new PrintWriter(stackTrace));
            stackTrace.flush();
            trace = stackTrace.toString();
        }

        ApiError apiError = new ApiError()
                .setStatus(ex.getStatus().value())
                .setError(ex.getStatus().name())
                .setMessage(ex.getMessage())
                .setCode(ex.getReason())
                .setPath(request.getServletPath())
                .setTimestamp(System.currentTimeMillis())
                .setException(ex.toString())
                .setTrace(trace);

        return apiError;
    }
}
