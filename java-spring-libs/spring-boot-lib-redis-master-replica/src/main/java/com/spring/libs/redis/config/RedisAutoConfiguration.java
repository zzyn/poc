package com.spring.libs.redis.config;

import com.spring.libs.redis.service.RedisService;
import com.spring.libs.redis.service.impl.RedisServiceDefaultImpl;
import com.spring.libs.redis.constants.RedisConstants;
import io.lettuce.core.ReadFrom;
import io.lettuce.core.models.role.RedisNodeDescription;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStaticMasterReplicaConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class RedisAutoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(RedisAutoConfiguration.class);

    private final AtomicInteger index = new AtomicInteger(-1);

    @Value("${spring.redis.lettuce.pool.max-active}")
    private int maxActive;

    @Value("${spring.redis.lettuce.pool.max-wait}")
    private Duration maxWait;

    @Value("${spring.redis.lettuce.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.lettuce.pool.min-idle}")
    private int minIdle;

    @Value("${spring.redis.app-enabled}")
    private boolean enabled;

    @Value("${spring.redis.app-ttl}")
    private Duration ttl;

    @Value("${spring.redis.app-prefix}")
    private String prefix;

    @Value("${spring.profiles.active}")
    private String profile;

    @Bean
    @ConditionalOnMissingBean
    public RedisConfig getRedisConfig() {
        return new RedisConfig(enabled, prefix, ttl, profile);
    }

    @Bean
    @ConditionalOnMissingBean
    public RedisMasterSlaveConfigProperties redisMasterSlaveConfigProperties(){
        return new RedisMasterSlaveConfigProperties();
    }

    @Bean
    @ConditionalOnMissingBean
    public GenericObjectPoolConfig genericObjectPoolConfig(){
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxWaitMillis(maxWait.toMillis());
        poolConfig.setMaxTotal(maxActive);
        return poolConfig;
    }

    @Bean
    @ConditionalOnMissingBean
    public RedisConnectionFactory redisConnectionFactory(RedisMasterSlaveConfigProperties masterSlaveConfigProperties, GenericObjectPoolConfig poolConfig) {

        RedisHostInfo master = masterSlaveConfigProperties.getMasterHostInfo();

        List<RedisHostInfo> slaves = masterSlaveConfigProperties.getSlaveInfos();

        RedisStaticMasterReplicaConfiguration masterReplicaConfiguration = new RedisStaticMasterReplicaConfiguration(master.getHost(), master.getPort());

        masterReplicaConfiguration.setDatabase(masterSlaveConfigProperties.getDatabase());

        if (Objects.nonNull(slaves) && !slaves.isEmpty()) {

            slaves
                    .stream()
                    .forEachOrdered(x -> {
                        masterReplicaConfiguration.addNode(x.getHost(), x.getPort());
                    });
        }

        LettuceClientConfiguration clientConfig = LettucePoolingClientConfiguration
                .builder()
                .poolConfig(poolConfig)
                .commandTimeout(masterSlaveConfigProperties.getTimeout())
                .readFrom(new ReadFrom() {
                    @Override
                    public List<RedisNodeDescription> select(Nodes nodes) {
                        List<RedisNodeDescription> allNodes = nodes.getNodes();
                        int ind = Math.abs(index.incrementAndGet() % allNodes.size());
                        RedisNodeDescription selected = allNodes.get(ind);
                        logger.info("Selected random node {} with uri {}", ind, selected.getUri());
                        List<RedisNodeDescription> remaining = IntStream.range(0, allNodes.size())
                                .filter(i -> i != ind)
                                .mapToObj(allNodes::get).collect(toList());
                        return Stream.concat(
                                Stream.of(selected),
                                remaining.stream()
                        ).collect(toList());
                    }
                })
                .build();

        return new LettuceConnectionFactory(masterReplicaConfiguration, clientConfig);
    }


    @Bean
    @ConditionalOnMissingBean
    public RedisTemplate<String, Object> getRedisTemplate(RedisConnectionFactory factory) {
        final GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        final StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        return new RedisTemplate<String, Object>() {{
            setConnectionFactory(factory);
            setDefaultSerializer(genericJackson2JsonRedisSerializer);
            setEnableDefaultSerializer(true);
            setKeySerializer(stringRedisSerializer);
            setHashKeySerializer(stringRedisSerializer);
            setValueSerializer(genericJackson2JsonRedisSerializer);
            setHashValueSerializer(genericJackson2JsonRedisSerializer);
            afterPropertiesSet();
        }};
    }

    @Bean
    @ConditionalOnMissingBean
    public RedisService getRedisService(RedisTemplate<String, Object> redisTemplate, RedisConfig config) {
        return new RedisServiceDefaultImpl(redisTemplate, config);
    }

    @Bean
    @ConditionalOnMissingBean
    public CacheManager getCacheManager(RedisConnectionFactory factory, RedisConfig config) {
        return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(factory))
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(config.getTtl())
                        .disableCachingNullValues()
                        .prefixKeysWith(config.getPrefix().concat(RedisConstants.COLON))
                        .serializeValuesWith(RedisSerializationContext.SerializationPair
                                .fromSerializer(new GenericJackson2JsonRedisSerializer())))
                .build();
    }
}
