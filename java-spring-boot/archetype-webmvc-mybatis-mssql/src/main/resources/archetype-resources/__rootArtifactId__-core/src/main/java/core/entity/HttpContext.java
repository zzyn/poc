package ${groupId}.core.entity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

public class HttpContext implements Serializable {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpContext setRequest(HttpServletRequest request) {
        this.request = request;
        return this;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public HttpContext setResponse(HttpServletResponse response) {
        this.response = response;
        return this;
    }
}
