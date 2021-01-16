package ${groupId}.core.bind;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpContextMvcArgumentResolverConfig {

    @Bean
    public HttpContextMvcArgumentResolver getHttpContextResolver() {

        return new HttpContextMvcArgumentResolver();
    }
}
