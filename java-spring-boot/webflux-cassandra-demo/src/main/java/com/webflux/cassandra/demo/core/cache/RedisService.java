package com.webflux.cassandra.demo.core.cache;

import java.io.Serializable;
import java.time.Duration;
import java.util.List;

public interface RedisService {

    <T extends Serializable> T get(String key, Class<T> clazz);

    <T extends Serializable> Boolean set(String key, T val);

    <T extends Serializable> Boolean set(String key, T val, Duration duration);

    <T extends Serializable> List<T> getSet(String key, Class<T> clazz);

    <T extends Serializable> Boolean setSet(String key, List<T> list);

    <T extends Serializable> Boolean setSet(String key, List<T> list, Duration duration);

    Boolean delete(String key);

    Boolean hasKey(String key);
}
