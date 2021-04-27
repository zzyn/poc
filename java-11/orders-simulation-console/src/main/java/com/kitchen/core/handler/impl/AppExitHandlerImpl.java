package com.kitchen.core.handler.impl;

import com.kitchen.core.handler.AppExitHandler;
import com.kitchen.dataproviders.CountDownLatchProvider;

import javax.inject.Inject;

public class AppExitHandlerImpl implements AppExitHandler {

    private final CountDownLatchProvider countDownLatchProvider;

    @Inject
    public AppExitHandlerImpl(CountDownLatchProvider countDownLatchProvider){
        this.countDownLatchProvider = countDownLatchProvider;
    }

    @Override
    public void exit() throws Exception {
        this.countDownLatchProvider.getLatch().await();
        System.exit(0);
    }
}
