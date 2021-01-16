package ${groupId}.core.exception;

import java.io.Serializable;

public class ApiError implements Serializable {

    private Long timestamp;
    private String path;
    private Integer status;
    private String error;
    private String code;
    private String message;
    private String exception;
    private String trace;

    public Long getTimestamp() {
        return timestamp;
    }

    public ApiError setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public ApiError setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ApiError setCode(String code) {
        this.code = code;
        return this;
    }

    public String getError() {
        return error;
    }

    public ApiError setError(String error) {
        this.error = error;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ApiError setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getException() {
        return exception;
    }

    public ApiError setException(String exception) {
        this.exception = exception;
        return this;
    }

    public String getTrace() {
        return trace;
    }

    public ApiError setTrace(String trace) {
        this.trace = trace;
        return this;
    }

    public String getPath() {
        return path;
    }

    public ApiError setPath(String path) {
        this.path = path;
        return this;
    }
}
