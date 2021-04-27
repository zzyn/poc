package com.kitchen.mock.impl;

import com.kitchen.core.handler.AppExitHandler;
import com.kitchen.dataproviders.CountDownLatchProvider;

import javax.inject.Inject;
import java.util.concurrent.CountDownLatch;

public class MockAppExitHandlerCountDownLatchImpl implements AppExitHandler {

    private final CountDownLatchProvider countDownLatchProvider;

    private final CountDownLatch testLatch;

    @Inject
    public MockAppExitHandlerCountDownLatchImpl(CountDownLatch testLatch, CountDownLatchProvider countDownLatchProvider){
        this.countDownLatchProvider = countDownLatchProvider;
        this.testLatch = testLatch;
    }

    @Override
    public void exit() throws Exception {
        this.countDownLatchProvider.getLatch().await();
        testLatch.countDown();
    }
}
