package com.webflux.cassandra.demo.core.cache;

public class RedisUtils {

    public static String getFullKey(String prefix, String profile, String key){

        return String.format("%s_%s_%s", prefix, profile, key);
    }
}
