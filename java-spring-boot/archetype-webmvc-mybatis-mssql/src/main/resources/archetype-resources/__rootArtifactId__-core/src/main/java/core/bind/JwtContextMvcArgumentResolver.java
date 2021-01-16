package ${groupId}.core.bind;

import ${groupId}.core.config.KeyConfig;
import ${groupId}.core.entity.IdentityToken;
import ${groupId}.core.entity.JwtContext;
import ${groupId}.core.security.IdentityTokenUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.ResourceLoader;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

import static ${groupId}.core.CoreConstants.ACCESS_TOKEN_HEADER;
import static ${groupId}.core.CoreConstants.ID_TOKEN_HEADER;

public class JwtContextMvcArgumentResolver implements HandlerMethodArgumentResolver {

    private final ResourceLoader resourceLoader;
    private final KeyConfig keyConfig;

    @Autowired
    public JwtContextMvcArgumentResolver(ResourceLoader resourceLoader, KeyConfig keyConfig) {
        this.resourceLoader = resourceLoader;
        this.keyConfig = keyConfig;
    }

    /**
     * Whether the given {@linkplain MethodParameter method parameter} is
     * supported by this resolver.
     *
     * @param methodParameter the method parameter to check
     * @return {@code true} if this resolver supports the supplied parameter;
     * {@code false} otherwise
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return Objects.nonNull(methodParameter.getParameterAnnotation(Jwt.class));
    }

    /**
     * Resolves a method parameter into an argument value from a given request.
     * A {@link ModelAndViewContainer} provides access to the model for the
     * request. A {@link WebDataBinderFactory} provides a way to create
     * a {@link WebDataBinder} instance when needed for data binding and
     * type conversion purposes.
     *
     * @param methodParameter     the method parameter to resolve. This parameter must
     *                      have previously been passed to {@link #supportsParameter} which must
     *                      have returned {@code true}.
     * @param mavContainer  the ModelAndViewContainer for the current request
     * @param webRequest    the current request
     * @param binderFactory a factory for creating {@link WebDataBinder} instances
     * @return the resolved argument value, or {@code null} if not resolvable
     * @throws Exception in case of errors with the preparation of argument values
     */
    @Nullable
    @Override
    public Object resolveArgument(MethodParameter methodParameter, @Nullable ModelAndViewContainer mavContainer, NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception {
        JwtContext context = new JwtContext();

        if (methodParameter.getParameterType().isAssignableFrom(JwtContext.class)) {

            String identityToken = webRequest.getHeader(ID_TOKEN_HEADER);
            String accessToken = webRequest.getHeader(ACCESS_TOKEN_HEADER);

            if (Objects.nonNull(identityToken) || Objects.nonNull(accessToken)) {

                if (Objects.nonNull(identityToken)) {

                    try {
                        IdentityToken idToken = IdentityTokenUtils.getIdentityToken(identityToken, resourceLoader, keyConfig);
                        context.setIdentityToken(idToken);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                if (Objects.nonNull(accessToken)) {

                }
            }

        }

        return context;
    }
}