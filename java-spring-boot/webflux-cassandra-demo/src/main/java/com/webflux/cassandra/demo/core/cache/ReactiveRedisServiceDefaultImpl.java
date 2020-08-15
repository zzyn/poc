package com.webflux.cassandra.demo.core.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.time.Duration;
import java.util.List;

import static com.webflux.cassandra.demo.core.CoreConstants.REDIS_SVC_PREFIX;
import static com.webflux.cassandra.demo.core.cache.RedisUtils.getFullKey;

@Service
public class ReactiveRedisServiceDefaultImpl implements ReactiveRedisService {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    private final ReactiveRedisTemplate<String, Object> reactiveRedisTemplate;

    @Autowired
    public ReactiveRedisServiceDefaultImpl(ReactiveRedisTemplate<String, Object> reactiveRedisTemplate) {
        this.reactiveRedisTemplate = reactiveRedisTemplate;
    }

    @Override
    public <T extends Serializable> Mono<T> get(String key, Class<T> clazz) {
        return reactiveRedisTemplate.opsForValue().get(getFullKey(REDIS_SVC_PREFIX, activeProfile, key)).cast(clazz);
    }

    @Override
    public <T extends Serializable> Mono<Boolean> set(String key, T val) {
        return reactiveRedisTemplate.opsForValue().set(getFullKey(REDIS_SVC_PREFIX, activeProfile, key), val);
    }

    @Override
    public <T extends Serializable> Mono<Boolean> set(String key, T val, Duration duration) {
        return reactiveRedisTemplate.opsForValue().set(getFullKey(REDIS_SVC_PREFIX, activeProfile, key), val, duration);
    }

    @Override
    public <T extends Serializable> Flux<T> getSet(String key, Class<T> clazz) {
        return reactiveRedisTemplate.opsForValue().get(getFullKey(REDIS_SVC_PREFIX, activeProfile, key)).flatMapMany(data-> {
            List<T> list = (List<T>) data;
            Flux<T> flux = Flux.fromIterable(list);
            return flux;
        });
    }

    @Override
    public <T extends Serializable> Mono<Boolean> setSet(String key, List<T> list) {
        return reactiveRedisTemplate.opsForValue().set(getFullKey(REDIS_SVC_PREFIX, activeProfile, key), list);
    }

    @Override
    public <T extends Serializable> Mono<Boolean> setSet(String key, List<T> list, Duration duration) {
        return reactiveRedisTemplate.opsForValue().set(getFullKey(REDIS_SVC_PREFIX, activeProfile, key), list, duration);
    }

    @Override
    public Mono<Boolean> delete(String key) {
        return reactiveRedisTemplate.delete(getFullKey(REDIS_SVC_PREFIX, activeProfile, key)).map(value->{
            return value > 0;
        });
    }

    @Override
    public Mono<Boolean> hasKey(String key) {
        return reactiveRedisTemplate.hasKey(getFullKey(REDIS_SVC_PREFIX, activeProfile, key));
    }
}