package ${groupId}.core.bind;

import ${groupId}.core.config.KeyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class JwtContextMvcArgumentResolverConfig {

    private final ResourceLoader resourceLoader;
    private final KeyConfig keyConfig;

    @Autowired
    public JwtContextMvcArgumentResolverConfig(ResourceLoader resourceLoader, KeyConfig keyConfig) {

        this.resourceLoader = resourceLoader;
        this.keyConfig = keyConfig;
    }

    @Bean
    public JwtContextMvcArgumentResolver getJwtContextResolver() {

        return new JwtContextMvcArgumentResolver(resourceLoader, keyConfig);
    }
}