package com.sdk.feign.auth;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.auth.BasicAuthRequestInterceptor;
import lombok.Getter;
import lombok.Setter;

/**
 * An interceptor that adds the request header needed to use HTTP basic authentication.
 */
@Getter
@Setter
public class HttpBasicAuth implements RequestInterceptor {

    private String username;
    private String password;

    public void setCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

  @Override
  public void apply(RequestTemplate template) {
      RequestInterceptor requestInterceptor = new BasicAuthRequestInterceptor(username, password);
      requestInterceptor.apply(template);
  }
}
