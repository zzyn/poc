package com.kitchen.mock.impl;

import com.kitchen.core.handler.AppExitHandler;
import com.kitchen.dataproviders.CountDownLatchProvider;
import com.kitchen.dataproviders.EventStorageProvider;
import com.kitchen.dataproviders.dto.Event;
import com.kitchen.dataproviders.dto.EventType;

import javax.inject.Inject;
import java.util.UUID;

public class MockAppExitHandlerImpl implements AppExitHandler {

    private final EventStorageProvider eventStorageProvider;
    private final CountDownLatchProvider countDownLatchProvider;

    @Inject
    public MockAppExitHandlerImpl(EventStorageProvider eventStorageProvider, CountDownLatchProvider countDownLatchProvider) {
        this.eventStorageProvider = eventStorageProvider;
        this.countDownLatchProvider = countDownLatchProvider;
    }

    @Override
    public void exit() throws Exception{
        eventStorageProvider.store(
                Event.builder()
                        .id(UUID.randomUUID())
                        .date(System.currentTimeMillis())
                        .reason("No Order")
                        .creator("APP")
                        .action(EventType.APP_EXIT)
                        .build());

        //this.countDownLatchProvider.getLatch().await();
    }
}
