package com.kitchen.core.handler.impl;

import com.kitchen.core.handler.KitchenOrderObserverHandler;
import com.kitchen.core.usercase.KitchenPutOrderInShelvesRoom;
import com.kitchen.dataproviders.EventStorageProvider;
import com.kitchen.dataproviders.dto.Event;
import com.kitchen.dataproviders.dto.EventType;
import com.kitchen.dataproviders.dto.Order;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.util.Objects;

@Slf4j
public class KitchenOrderObserverHandlerImpl implements KitchenOrderObserverHandler {

    private static final String CREATOR_TPL = "Kitchen-%s";
    private final EventStorageProvider eventStorageProvider;
    private final KitchenPutOrderInShelvesRoom kitchenPutOrderInShelvesRoom;

    @Inject
    public KitchenOrderObserverHandlerImpl(EventStorageProvider eventStorageProvider, KitchenPutOrderInShelvesRoom kitchenPutOrderInShelvesRoom) {
        Objects.requireNonNull(eventStorageProvider, "eventStorageProvider is required");
        Objects.requireNonNull(kitchenPutOrderInShelvesRoom, "kitchenPutOrderInShelvesRoom is required");
        this.eventStorageProvider = eventStorageProvider;
        this.kitchenPutOrderInShelvesRoom = kitchenPutOrderInShelvesRoom;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull Order order) {

        eventStorageProvider.store(Event
                .builder()
                .action(EventType.KITCHEN_ACCEPT_ORDER)
                .date(System.currentTimeMillis())
                .order(order)
                .creator(String.format(CREATOR_TPL, Thread.currentThread().getId()))
                .build());

        eventStorageProvider.store(Event
                .builder()
                .action(EventType.KITCHEN_COOKED_ORDER)
                .date(System.currentTimeMillis())
                .order(order)
                .creator(String.format(CREATOR_TPL, Thread.currentThread().getId()))
                .build());

        kitchenPutOrderInShelvesRoom.execute(order);

        eventStorageProvider.store(Event
                .builder()
                .action(EventType.KITCHEN_PUT_ORDER_TO_SHELVES)
                .date(System.currentTimeMillis())
                .order(order)
                .creator(String.format(CREATOR_TPL, Thread.currentThread().getId()))
                .build());
    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
