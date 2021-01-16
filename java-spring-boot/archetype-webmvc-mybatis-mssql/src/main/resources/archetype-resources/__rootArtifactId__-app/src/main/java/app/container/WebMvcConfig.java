package ${groupId}.app.container;

import ${groupId}.core.bind.HttpContextMvcArgumentResolver;
import ${groupId}.core.bind.JwtContextMvcArgumentResolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import java.util.List;

@Configuration
public class WebMvcConfig extends DelegatingWebMvcConfiguration {

    private JwtContextMvcArgumentResolver jwtContextMvcArgumentResolver;
    private HttpContextMvcArgumentResolver httpContextMvcArgumentResolver;

    @Autowired
    public WebMvcConfig(JwtContextMvcArgumentResolver jwtContextMvcArgumentResolver, HttpContextMvcArgumentResolver httpContextMvcArgumentResolver) {
        this.jwtContextMvcArgumentResolver = jwtContextMvcArgumentResolver;
        this.httpContextMvcArgumentResolver = httpContextMvcArgumentResolver;
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
    protected void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index.html");
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(jwtContextMvcArgumentResolver);
        argumentResolvers.add(httpContextMvcArgumentResolver);
    }
}
