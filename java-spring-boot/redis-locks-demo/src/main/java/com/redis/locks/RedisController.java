package com.redis.locks;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/redis")
public class RedisController {

    private final RedissonClient redissonClient;

    @Autowired
    public RedisController(RedissonClient redissonClient){
        this.redissonClient = redissonClient;
    }


    @GetMapping(path = "/lock/{key}")
    public boolean lock(@PathVariable("key") String key){

        long waitSeconds = 300;

        RLock lock =  redissonClient.getLock(key);

        try {
           return lock.tryLock(waitSeconds, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        log.info("LOCK" + lock.getName());
        return false;
    }

    @GetMapping(path = "/release/{key}")
    public boolean release(@PathVariable("key")String key){

        RLock lock =  redissonClient.getLock(key);
        log.info("UNLOCK" + lock.getName());


        if(lock.isLocked()){
            return lock.forceUnlock();
        }
        return false;
    }
}
