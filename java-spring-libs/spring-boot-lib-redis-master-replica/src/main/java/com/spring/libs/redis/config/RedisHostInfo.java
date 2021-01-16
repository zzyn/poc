package com.spring.libs.redis.config;

public class RedisHostInfo {

    private String host;
    private int port;

    public String getHost() {
        return host;
    }

    public RedisHostInfo setHost(String host) {
        this.host = host;
        return this;
    }

    public int getPort() {
        return port;
    }

    public RedisHostInfo setPort(int port) {
        this.port = port;
        return this;
    }
}
