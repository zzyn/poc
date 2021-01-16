package com.spring.libs.redis.function;

@FunctionalInterface
public interface CheckedExceptionSupplier<T> {
    T get() throws Exception;
}
