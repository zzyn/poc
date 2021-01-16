package com.spring.libs.redis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ConfigurationProperties(prefix = "spring.redis")
public class RedisMasterSlaveConfigProperties {

    private String master;
    private List<String> slaves;
    private int database;
    private Duration timeout;

    public String getMaster() {
        return master;
    }

    public RedisMasterSlaveConfigProperties setMaster(String master) {
        this.master = master;
        return this;
    }

    public List<String> getSlaves() {
        return slaves;
    }

    public RedisMasterSlaveConfigProperties setSlaves(List<String> slaves) {
        this.slaves = slaves;
        return this;
    }

    public int getDatabase() {
        return database;
    }

    public RedisMasterSlaveConfigProperties setDatabase(int database) {
        this.database = database;
        return this;
    }

    public Duration getTimeout() {
        return timeout;
    }

    public RedisMasterSlaveConfigProperties setTimeout(Duration timeout) {
        this.timeout = timeout;
        return this;
    }

    public RedisHostInfo getMasterHostInfo(){
        String[] data = master.split(":");
        return new RedisHostInfo()
                .setHost(data[0])
                .setPort(Integer.valueOf(data[1]));
    }

    public List<RedisHostInfo> getSlaveInfos(){
        List<RedisHostInfo> list = new ArrayList<>();

        if(Objects.nonNull(slaves) && !slaves.isEmpty()){
            slaves.stream().forEachOrdered(x->{
                String[] data = x.split(":");
                list.add(new RedisHostInfo()
                        .setHost(data[0])
                        .setPort(Integer.valueOf(data[1])));
            });
        }
        return list;
    }
}
