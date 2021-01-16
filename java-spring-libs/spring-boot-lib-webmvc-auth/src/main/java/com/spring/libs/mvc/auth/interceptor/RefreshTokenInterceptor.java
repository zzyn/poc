package com.spring.libs.mvc.auth.interceptor;

import com.spring.libs.mvc.auth.annotation.RequiredRefreshToken;
import com.spring.libs.mvc.auth.utils.TokenInterceptorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.PublicKey;

import static com.spring.libs.mvc.auth.constants.AuthConstants.REFRESH_TOKEN_HEADER;

@Slf4j
public class RefreshTokenInterceptor implements HandlerInterceptor {

    private final PublicKey publicKey;

    public RefreshTokenInterceptor(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            if (((HandlerMethod) handler).hasMethodAnnotation(RequiredRefreshToken.class) && !HttpMethod.OPTIONS.matches(request.getMethod())) {
                return TokenInterceptorUtils.verifyToken(request, REFRESH_TOKEN_HEADER, publicKey);
            }
        }
        return true;
    }
}
