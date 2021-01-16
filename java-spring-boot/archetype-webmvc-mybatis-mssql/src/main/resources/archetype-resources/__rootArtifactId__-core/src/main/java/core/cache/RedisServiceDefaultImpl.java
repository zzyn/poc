package ${groupId}.core.cache;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.time.Duration;

@Service
public class RedisServiceDefaultImpl implements RedisService {

    @Resource
    private RedisTemplate<Serializable, Serializable> redisTemplate;

    /**
     * get class by key
     *
     * @param key
     * @return
     */
    @Override
    public <T extends Serializable> T get(String key) {
        Serializable val = redisTemplate.opsForValue().get(key);
        if (val == null) {
            return null;
        }
        return ((T) val);
    }

    /**
     * delete key
     *
     * @param key
     * @return
     */
    @Override
    public boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * @param key
     * @param object
     * @return
     */
    @Override
    public void set(String key, Serializable object) {

        redisTemplate.opsForValue().set(key, object);
    }

    /**
     * @param key
     * @param object
     * @param timeout
     * @return
     */
    @Override
    public void set(String key, Serializable object, Duration timeout) {
        redisTemplate.opsForValue().set(key, object, timeout);
    }

    /**
     * @param key
     * @return
     */
    @Override
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }
}
