package com.spring.libs.redis.function;

@FunctionalInterface
public interface CheckedExceptionFunction<T, R> {
    R apply(T t) throws Exception;
}
