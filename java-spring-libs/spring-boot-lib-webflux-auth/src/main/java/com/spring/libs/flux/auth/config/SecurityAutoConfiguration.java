package com.spring.libs.flux.auth.config;

import com.spring.libs.flux.auth.core.JwtAuthManager;
import com.spring.libs.flux.auth.core.JwtTokenExtractor;
import com.spring.libs.flux.auth.core.entities.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.HttpStatusServerEntryPoint;
import org.springframework.security.web.server.authentication.ServerAuthenticationEntryPointFailureHandler;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.security.web.server.util.matcher.NegatedServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Profile("!test")
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@ComponentScan(basePackages = "com.ef.edtech.kt.security")
@Import({KeyConfig.class, AuthClientConfig.class})
class SecurityAutoConfiguration {

    private static final String[] SYS_URL_LIST = new String[]{
            // swagger ui
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**",
            // Actuator
            "/actuator/**"
    };

    private final JwtAuthManager jwtAuthManager;
    private final JwtTokenExtractor jwtTokenExtractor;
    private final SecurityProperties securityProperties;

    @Autowired
    public SecurityAutoConfiguration(
            JwtAuthManager jwtAuthManager,
            JwtTokenExtractor jwtTokenExtractor,
            SecurityProperties securityProperties) {
        this.jwtAuthManager = jwtAuthManager;
        this.jwtTokenExtractor = jwtTokenExtractor;
        this.securityProperties = securityProperties;
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .addFilterAt(authenticationFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }

    private AuthenticationWebFilter authenticationFilter() {
        AuthenticationWebFilter filter = new AuthenticationWebFilter(jwtAuthManager);
        filter.setServerAuthenticationConverter(jwtTokenExtractor);
        ServerAuthenticationFailureHandler authenticationFailureHandler = new ServerAuthenticationEntryPointFailureHandler(new HttpStatusServerEntryPoint(HttpStatus.FORBIDDEN));
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);

        Collection<String> whitelist = new HashSet<String>(Arrays.asList(SYS_URL_LIST));
        List<String> excludedPaths = securityProperties.getExcludePaths();
        if (!CollectionUtils.isEmpty(excludedPaths)) {
            whitelist.addAll(excludedPaths);
        }

        filter.setRequiresAuthenticationMatcher(
                new NegatedServerWebExchangeMatcher(
                        ServerWebExchangeMatchers.pathMatchers(whitelist.stream().toArray(String[]::new))
                )
        );

        return filter;
    }
}
