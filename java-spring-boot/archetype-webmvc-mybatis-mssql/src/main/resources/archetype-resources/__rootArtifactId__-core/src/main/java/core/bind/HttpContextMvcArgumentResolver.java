package ${groupId}.core.bind;

import ${groupId}.core.entity.HttpContext;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class HttpContextMvcArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Objects.nonNull(parameter.getParameterAnnotation(Http.class));
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpContext context = new HttpContext();

        if (parameter.getParameterType().isAssignableFrom(HttpContext.class)) {

            if (Objects.nonNull(webRequest)) {
                context.setRequest((HttpServletRequest)webRequest.getNativeRequest());
                context.setResponse((HttpServletResponse)webRequest.getNativeResponse());
            }
        }
        return context;
    }
}
