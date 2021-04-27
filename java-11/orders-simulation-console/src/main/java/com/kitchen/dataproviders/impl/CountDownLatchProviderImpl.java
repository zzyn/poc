package com.kitchen.dataproviders.impl;

import com.kitchen.dataproviders.CountDownLatchProvider;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchProviderImpl implements CountDownLatchProvider {

    private static CountDownLatch builder() {
        return new CountDownLatch(1);
    }

    public static CountDownLatch getInstance() {
        return CountDownLatchProviderImpl.Holder.instance;
    }

    @Override
    public CountDownLatch getLatch() {
        return CountDownLatchProviderImpl.getInstance();
    }

    private static class Holder {
        public static CountDownLatch instance = CountDownLatchProviderImpl.builder();
    }
}
