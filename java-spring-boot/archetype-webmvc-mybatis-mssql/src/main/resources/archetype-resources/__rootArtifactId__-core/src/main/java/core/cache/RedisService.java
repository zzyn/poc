package ${groupId}.core.cache;

import java.io.Serializable;
import java.time.Duration;

public interface RedisService {

    /**
     * get class by key
     *
     * @param key
     * @return
     */
    public <T extends Serializable> T get(String key);

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
