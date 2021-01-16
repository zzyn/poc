package com.spring.libs.redis.service;

import com.spring.libs.redis.function.CheckedExceptionFunction;
import com.spring.libs.redis.function.CheckedExceptionSupplier;

import java.io.Serializable;
import java.time.Duration;
import java.util.List;

public interface RedisService {

    <T extends Serializable> T get(String key);

    <T, R extends Serializable> R getAndSet(String key, CheckedExceptionFunction<T, R> func, T t) throws Exception;

    <T, R extends Serializable> R getAndSet(String key, CheckedExceptionFunction<T, R> func, T t, Duration duration) throws Exception;

    <T extends Serializable> T getAndSet(String key, CheckedExceptionSupplier<T> func) throws Exception;

    <T extends Serializable> T getAndSet(String key, CheckedExceptionSupplier<T> func, Duration duration) throws Exception;

    <T extends Serializable> Boolean set(String key, T val);

    <T extends Serializable> Boolean set(String key, T val, Duration duration);

    <T extends Serializable> List<T> getList(String key);

    <T extends Serializable> Boolean setList(String key, List<T> list);

    <T extends Serializable> Boolean setList(String key, List<T> list, Duration duration);

    Boolean delete(String key);

    Boolean hasKey(String key);
}
