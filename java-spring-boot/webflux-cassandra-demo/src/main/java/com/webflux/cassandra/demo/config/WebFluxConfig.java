package com.webflux.cassandra.demo.config;

import com.webflux.cassandra.demo.core.bind.ConsistencyContextArgumentResolver;
import com.webflux.cassandra.demo.core.bind.JwtContextArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

@Configuration
@EnableWebFlux
public class WebFluxConfig implements WebFluxConfigurer {

    private final JwtContextArgumentResolver jwtContextArgumentResolver;
    private final ConsistencyContextArgumentResolver consistencyContextArgumentResolver;

    @Autowired
    public WebFluxConfig(JwtContextArgumentResolver jwtContextArgumentResolver, ConsistencyContextArgumentResolver consistencyContextArgumentResolver) {

        this.jwtContextArgumentResolver = jwtContextArgumentResolver;
        this.consistencyContextArgumentResolver = consistencyContextArgumentResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:public/");

        registry.addResourceHandler("/swagger-ui.html**")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {

        configurer.addCustomResolver(jwtContextArgumentResolver);
        configurer.addCustomResolver(consistencyContextArgumentResolver);
    }

    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {

        configurer.defaultCodecs().enableLoggingRequestDetails(false);
    }
}
