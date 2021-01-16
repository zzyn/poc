package com.spring.libs.prometheus.autoconfigure;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "management.endpoint.prometheus.enabled", matchIfMissing = true)
public class PrometheusMetricsAutoConfiguration {

    @Value("${spring.application.name:application}")
    private String appName;

    @Value("${spring.profiles.active:default}")
    private String activeProfile;

    @Bean
    @ConditionalOnMissingBean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry
            .config()
            .commonTags("application", String.format("%s (%s)", appName, activeProfile));
    }
}
