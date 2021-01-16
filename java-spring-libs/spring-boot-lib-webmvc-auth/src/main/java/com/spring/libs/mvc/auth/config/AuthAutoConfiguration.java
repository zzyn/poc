package com.spring.libs.mvc.auth.config;

import com.spring.libs.jwt.config.JwtAutoConfiguration;
import com.spring.libs.mvc.auth.aspect.AccessTokenDbValidatorAspect;
import com.spring.libs.mvc.auth.aspect.IdTokenDbValidatorAspect;
import com.spring.libs.mvc.auth.aspect.RefreshTokenDbValidatorAspect;
import com.spring.libs.mvc.auth.interceptor.AccessTokenInterceptor;
import com.spring.libs.mvc.auth.interceptor.IdTokenInterceptor;
import com.spring.libs.mvc.auth.interceptor.RefreshTokenInterceptor;
import com.spring.libs.mvc.auth.resolver.JwtContextMvcArgumentResolver;
import com.spring.libs.mvc.auth.service.AccessTokenDbValidateService;
import com.spring.libs.mvc.auth.service.IdTokenDbValidateService;
import com.spring.libs.mvc.auth.service.RefreshTokenDbValidateService;
import com.spring.libs.mvc.auth.service.impl.AccessTokenDbValidateServiceDefaultImpl;
import com.spring.libs.mvc.auth.service.impl.IdTokenDbValidateServiceDefaultImpl;
import com.spring.libs.mvc.auth.service.impl.RefreshTokenDbValidateServiceDefaultImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.PublicKey;

@Configuration
@AutoConfigureAfter(JwtAutoConfiguration.class)
public class AuthAutoConfiguration {

    @Value("${auth.path-patterns:/api/**}")
    private String pathPattern;

    @Bean
    public AuthConfig getAuthConfig() {
        return new AuthConfig(pathPattern.split(","));
    }

    @SuppressWarnings("all")
    @Bean
    @ConditionalOnBean(PublicKey.class)
    public JwtContextMvcArgumentResolver getJwtContextMvcArgumentResolver(PublicKey publicKey) {
        return new JwtContextMvcArgumentResolver(publicKey);
    }

    @SuppressWarnings("all")
    @Bean
    @ConditionalOnBean(PublicKey.class)
    public IdTokenInterceptor getIdTokenInterceptor(PublicKey publicKey) {
        return new IdTokenInterceptor(publicKey);
    }

    @SuppressWarnings("all")
    @Bean
    @ConditionalOnBean(PublicKey.class)
    public AccessTokenInterceptor getAccessTokenInterceptor(PublicKey publicKey) {
        return new AccessTokenInterceptor(publicKey);
    }

    @SuppressWarnings("all")
    @Bean
    @ConditionalOnBean(PublicKey.class)
    public RefreshTokenInterceptor getRefreshTokenInterceptor(PublicKey publicKey) {
        return new RefreshTokenInterceptor(publicKey);
    }

    @Bean
    @ConditionalOnMissingBean
    public IdTokenDbValidateService getIdTokenDbValidateService() {
        return new IdTokenDbValidateServiceDefaultImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public AccessTokenDbValidateService getAccessTokenDbValidateService() {
        return new AccessTokenDbValidateServiceDefaultImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public RefreshTokenDbValidateService getRefreshTokenDbValidateService() {
        return new RefreshTokenDbValidateServiceDefaultImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public IdTokenDbValidatorAspect getIdTokenDbValidatorAspect() {
        return new IdTokenDbValidatorAspect();
    }

    @Bean
    @ConditionalOnMissingBean
    public AccessTokenDbValidatorAspect getAccessTokenDbValidatorAspect() {
        return new AccessTokenDbValidatorAspect();
    }

    @Bean
    @ConditionalOnMissingBean
    public RefreshTokenDbValidatorAspect getRefreshTokenDbValidatorAspect() {
        return new RefreshTokenDbValidatorAspect();
    }

    @Bean
    @ConditionalOnMissingBean
    public WebMvcConfiguration getWebMvcConfiguration(AuthConfig authConfig, JwtContextMvcArgumentResolver jwtContextMvcArgumentResolver, IdTokenInterceptor idTokenInterceptor, AccessTokenInterceptor accessTokenInterceptor, RefreshTokenInterceptor refreshTokenInterceptor) {
        return new WebMvcConfiguration(authConfig, jwtContextMvcArgumentResolver, idTokenInterceptor, accessTokenInterceptor, refreshTokenInterceptor);
    }
}
