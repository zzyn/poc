package com.webflux.cassandra.demo.core.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.Duration;
import java.util.List;

import static com.webflux.cassandra.demo.core.CoreConstants.REDIS_SVC_PREFIX;

@Service
public class RedisServiceDefaultImpl implements RedisService {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    private final RedisTemplate<String, Object> redisTemplate;
    private final Logger logger = LoggerFactory.getLogger(RedisServiceDefaultImpl.class);

    @Autowired
    public RedisServiceDefaultImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public <T extends Serializable> T get(String key, Class<T> clazz) {
        return (T)redisTemplate.opsForValue().get(RedisUtils.getFullKey(REDIS_SVC_PREFIX, activeProfile, key));
    }

    @Override
    public <T extends Serializable> Boolean set(String key, T val) {

        String fullKey = RedisUtils.getFullKey(REDIS_SVC_PREFIX, activeProfile, key);

        try {
            redisTemplate.opsForValue().set(fullKey, val);
            return true;
        }
        catch (Exception e){
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public <T extends Serializable> Boolean set(String key, T val, Duration duration) {

        String fullKey = RedisUtils.getFullKey(REDIS_SVC_PREFIX, activeProfile, key);

        try {
            redisTemplate.opsForValue().set(fullKey, val, duration);
            return true;
        }
        catch (Exception e){
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public <T extends Serializable> List<T> getSet(String key, Class<T> clazz) {
        Object data = redisTemplate.opsForValue().get(RedisUtils.getFullKey(REDIS_SVC_PREFIX, activeProfile, key));
        List<T> list = (List<T>)data;
        return list;
    }

    @Override
    public <T extends Serializable> Boolean setSet(String key, List<T> list) {

        String fullKey = RedisUtils.getFullKey(REDIS_SVC_PREFIX, activeProfile, key);

        try {
            redisTemplate.opsForValue().set(fullKey, list);
            return true;
        }
        catch (Exception e){
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public <T extends Serializable> Boolean setSet(String key, List<T> list, Duration duration) {

        String fullKey = RedisUtils.getFullKey(REDIS_SVC_PREFIX, activeProfile, key);

        try {
            redisTemplate.opsForValue().set(fullKey, list, duration);
            return true;
        }
        catch (Exception e){
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Boolean delete(String key) {
        return redisTemplate.delete(RedisUtils.getFullKey(REDIS_SVC_PREFIX, activeProfile, key));
    }

    @Override
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(RedisUtils.getFullKey(REDIS_SVC_PREFIX, activeProfile, key));
    }
}
