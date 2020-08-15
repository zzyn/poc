package com.webflux.cassandra.demo.core.cache;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.time.Duration;
import java.util.List;

public interface ReactiveRedisService {

    <T extends Serializable> Mono<T> get(String key, Class<T> clazz);

    <T extends Serializable> Mono<Boolean> set(String key, T val);

    <T extends Serializable> Mono<Boolean> set(String key, T val, Duration duration);

    <T extends Serializable> Flux<T> getSet(String key, Class<T> clazz);

    <T extends Serializable> Mono<Boolean> setSet(String key, List<T> list);

    <T extends Serializable> Mono<Boolean> setSet(String key, List<T> list, Duration duration);

    Mono<Boolean> delete(String key);

    Mono<Boolean> hasKey(String key);
}
