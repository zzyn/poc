package com.spring.libs.mvc.auth.resolver;

import com.spring.libs.jwt.utils.TokenUtils;
import com.spring.libs.mvc.auth.annotation.Jwt;
import com.spring.libs.mvc.auth.model.AccessToken;
import com.spring.libs.mvc.auth.model.IdentityToken;
import com.spring.libs.mvc.auth.model.JwtContext;
import com.spring.libs.mvc.auth.model.RefreshToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.security.PublicKey;
import java.util.Objects;

import static com.spring.libs.mvc.auth.constants.AuthConstants.*;

@Slf4j
public class JwtContextMvcArgumentResolver implements HandlerMethodArgumentResolver {

    private final PublicKey publicKey;

    public JwtContextMvcArgumentResolver(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return Objects.nonNull(methodParameter.getParameterAnnotation(Jwt.class));
    }

    @Nullable
    @Override
    public Object resolveArgument(MethodParameter methodParameter, @Nullable ModelAndViewContainer mavContainer, NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception {

        JwtContext context = new JwtContext();

        if (methodParameter.getParameterType().isAssignableFrom(JwtContext.class)) {
            try {
                String identityToken = webRequest.getHeader(ID_TOKEN_HEADER);
                String accessToken = webRequest.getHeader(ACCESS_TOKEN_HEADER);
                String refreshToken = webRequest.getHeader(REFRESH_TOKEN_HEADER);

                context.setIdentityToken(StringUtils.isNotEmpty(identityToken) ? TokenUtils.decodeToken(identityToken, publicKey, IdentityToken.class).setRawData(identityToken) : null)
                        .setAccessToken(StringUtils.isNotEmpty(accessToken) ? TokenUtils.decodeToken(accessToken, publicKey, AccessToken.class).setRawData(accessToken) : null)
                        .setRefreshToken(StringUtils.isNotEmpty(refreshToken) ? TokenUtils.decodeToken(refreshToken, publicKey, RefreshToken.class).setRawData(refreshToken) : null);
            } catch (Exception e) {
                log.warn(e.getMessage(), e);
            }
        }

        return context;
    }
}