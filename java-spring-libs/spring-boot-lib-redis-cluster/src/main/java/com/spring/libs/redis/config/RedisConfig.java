package com.spring.libs.redis.config;

import java.time.Duration;

public class RedisConfig {

    private String prefix;

    private Duration ttl;

    private Boolean enabled;

    private String profile;

    public RedisConfig(Boolean enabled, String prefix, Duration ttl, String profile) {
        this.enabled = enabled;
        this.prefix = prefix;
        this.ttl = ttl;
        this.profile = profile;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Duration getTtl() {
        return ttl;
    }

    public void setTtl(Duration ttl) {
        this.ttl = ttl;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
