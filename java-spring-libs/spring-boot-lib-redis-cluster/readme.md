# Spring Boot Redis Cluster Library

### Latest Version
1.0.0

### Usage

Add dependency in ```pom.xml```

```
<dependency>
    <groupId>com.spring.libs</groupId>
    <artifactId>spring-boot-redis-cluster</artifactId>
    <version>1.0.0</version>
</dependency>
```

Add configuration in ```application.yml```

```yaml
spring:
  redis:
    cluster:
      nodes:
        - cnedtechstgrediscluster-1.nsmena.clustercfg.cnn1.cache.amazonaws.com.cn:6379
    database: 0
    lettuce:
      pool:
        max-active: 10000
        max-wait: 5000ms
        max-idle: 30
        min-idle: 5
    timeout: 5000ms
    app-enabled: true
    app-ttl: 30m
    app-prefix: "xxxxx"
```

Use [RedisService](src/main/java/com/spring/libs/redis/service/RedisService.java) to manipulate the redis cache.
 - the full key in redis would be constructed by {spring.redis.app-prefix}\_{spring.profiles.active}\_{key}