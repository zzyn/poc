package ${groupId}.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ApiException extends ResponseStatusException {

    public ApiException(HttpStatus status) {
        super(status);
    }

    public ApiException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public ApiException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }
}
