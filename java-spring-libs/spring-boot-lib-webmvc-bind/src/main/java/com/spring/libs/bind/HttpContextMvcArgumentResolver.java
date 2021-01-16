package com.spring.libs.bind;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class HttpContextMvcArgumentResolver implements HandlerMethodArgumentResolver {
    public boolean supportsParameter(MethodParameter methodParameter) {
        return Objects.nonNull(methodParameter.getParameterAnnotation(Http.class));
    }

    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpContext context = new HttpContext();

        if (methodParameter.getParameterType().isAssignableFrom(HttpContext.class)) {
            if (Objects.nonNull(nativeWebRequest)) {
                context.setRequest((HttpServletRequest) nativeWebRequest.getNativeRequest());
                context.setResponse((HttpServletResponse) nativeWebRequest.getNativeResponse());
            }
        }
        return context;
    }
}
