package com.spring.libs.redis.service.impl;

import com.spring.libs.redis.config.RedisConfig;
import com.spring.libs.redis.function.CheckedExceptionFunction;
import com.spring.libs.redis.function.CheckedExceptionSupplier;
import com.spring.libs.redis.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

import static com.spring.libs.redis.constants.RedisConstants.REDIS_KEY_FORMATTER;

public class RedisServiceDefaultImpl implements RedisService {

    private static final Logger logger = LoggerFactory.getLogger(RedisServiceDefaultImpl.class);

    private final RedisConfig config;

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisServiceDefaultImpl(RedisTemplate<String, Object> redisTemplate, RedisConfig config) {
        this.redisTemplate = redisTemplate;
        this.config = config;
    }

    @Override
    public <T extends Serializable> T get(String key) {
        return (T) getObject(key);
    }

    @Override
    public <T, R extends Serializable> R getAndSet(String key, CheckedExceptionFunction<T, R> func, T t) throws Exception {
        try {
            R data = (R) getObject(key);
            if (Objects.isNull(data) && Objects.nonNull(func)) {

                data = func.apply(t);

                if (Objects.nonNull(data)) {
                    setObject(key, data);
                }
            }
            return data;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public <T, R extends Serializable> R getAndSet(String key, CheckedExceptionFunction<T, R> func, T t, Duration duration) throws Exception {
        try {
            R data = (R) getObject(key);
            if (Objects.isNull(data) && Objects.nonNull(func)) {

                data = func.apply(t);

                if (Objects.nonNull(data)) {
                    setObject(key, data, duration);
                }
            }
            return data;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public <T extends Serializable> T getAndSet(String key, CheckedExceptionSupplier<T> func) throws Exception {
        try {
            T data = (T) getObject(key);
            if (Objects.isNull(data) && Objects.nonNull(func)) {

                data = func.get();

                if (Objects.nonNull(data)) {
                    setObject(key, data);
                }
            }

            return data;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public <T extends Serializable> T getAndSet(String key, CheckedExceptionSupplier<T> func, Duration duration) throws Exception {
        try {
            T data = (T) getObject(key);
            if (Objects.isNull(data) && Objects.nonNull(func)) {

                data = func.get();

                if (Objects.nonNull(data)) {
                    setObject(key, data, duration);
                }
            }
            return data;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }


    @Override
    public <T extends Serializable> Boolean set(String key, T val) {
        return setObject(key, val);
    }

    @Override
    public <T extends Serializable> Boolean set(String key, T val, Duration duration) {
        return setObject(key, val, duration);
    }

    @Override
    public <T extends Serializable> List<T> getList(String key) {
        return (List<T>) getObject(key);
    }

    @Override
    public <T extends Serializable> Boolean setList(String key, List<T> list) {
        return setObject(key, list);
    }

    @Override
    public <T extends Serializable> Boolean setList(String key, List<T> list, Duration duration) {
        return setObject(key, list, duration);
    }

    @Override
    public Boolean delete(String key) {
        return redisTemplate.delete(getFullKey(key));
    }

    @Override
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(getFullKey(key));
    }

    private Boolean setObject(String key, Object object, Duration duration) {

        if (!config.getEnabled()) {
            return true;
        }

        try {
            redisTemplate.opsForValue().set(getFullKey(key), object, duration);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    private Boolean setObject(String key, Object object) {

        if (!config.getEnabled()) {
            return true;
        }

        try {
            redisTemplate.opsForValue().set(getFullKey(key), object);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    private Object getObject(String key) {
        return config.getEnabled() ? redisTemplate.opsForValue().get(getFullKey(key)) : null;
    }

    public String getFullKey(String key) {
        return String.format(REDIS_KEY_FORMATTER, config.getPrefix(), config.getProfile(), key);
    }
}
