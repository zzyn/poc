package ${groupId}.core.cache;

import java.io.Serializable;
import java.time.Duration;

public interface RedisService {

    /**
     * get object form redis by key
     *
     * @param key
     * @return
     */
    public Object get(String key);

    /**
     * get class by key
     *
     * @param key
     * @param requiredType
     * @return
     */
    public <T> T get(String key, Class<? extends Serializable> requiredType);

    /**
     * delete key
     *
     * @param key
     * @return
     */
    public boolean delete(String key);

    /**
     * @param key
     * @param object
     * @return
     */
    public void set(String key, Serializable object);

    /**
     * @param key
     * @param object
     * @param timeout
     * @return
     */
    public void set(String key, Serializable object, Duration timeout);

    /**
     * @param key
     * @return
     */
    public boolean hasKey(String key);
}
