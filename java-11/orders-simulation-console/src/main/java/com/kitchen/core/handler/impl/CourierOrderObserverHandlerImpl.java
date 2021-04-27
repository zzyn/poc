package com.kitchen.core.handler.impl;

import com.kitchen.core.handler.CourierOrderObserverHandler;
import com.kitchen.core.usercase.CourierPickupOrderFromShelvesRoom;
import com.kitchen.dataproviders.CountDownLatchProvider;
import com.kitchen.dataproviders.EventStorageProvider;
import com.kitchen.dataproviders.OrdersProvider;
import com.kitchen.dataproviders.dto.Event;
import com.kitchen.dataproviders.dto.EventType;
import com.kitchen.dataproviders.dto.Order;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.util.Objects;

@Slf4j
public class CourierOrderObserverHandlerImpl implements CourierOrderObserverHandler {

    private static final String CREATOR_TPL = "Courier-%s";
    private final EventStorageProvider eventStorageProvider;
    private final CourierPickupOrderFromShelvesRoom courierPickupOrderFromShelvesRoom;
    private final OrdersProvider ordersProvider;
    private final CountDownLatchProvider countDownLatchProvider;

    @Inject
    public CourierOrderObserverHandlerImpl(EventStorageProvider eventStorageProvider, CourierPickupOrderFromShelvesRoom courierPickupOrderFromShelvesRoom, OrdersProvider ordersProvider, CountDownLatchProvider countDownLatchProvider) {

        Objects.requireNonNull(countDownLatchProvider, "countDownLatchProvider is required");
        Objects.requireNonNull(ordersProvider, "ordersProvider is required");
        Objects.requireNonNull(eventStorageProvider, "eventStorageProvider is required");
        Objects.requireNonNull(courierPickupOrderFromShelvesRoom, "courierPickupOrderFromShelvesRoom is required");

        this.countDownLatchProvider = countDownLatchProvider;
        this.eventStorageProvider = eventStorageProvider;
        this.courierPickupOrderFromShelvesRoom = courierPickupOrderFromShelvesRoom;
        this.ordersProvider = ordersProvider;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull Order order) {

        eventStorageProvider.store(Event
                .builder()
                .action(EventType.COURIER_ACCEPT_ORDER)
                .date(System.currentTimeMillis())
                .order(order)
                .creator(String.format(CREATOR_TPL, Thread.currentThread().getId()))
                .build());

        Order shelfOrder = courierPickupOrderFromShelvesRoom.execute(order);

        if (Objects.nonNull(shelfOrder)) {

            eventStorageProvider.store(Event
                    .builder()
                    .action(EventType.COURIER_PICKUP_ORDER)
                    .date(System.currentTimeMillis())
                    .order(order)
                    .creator(String.format(CREATOR_TPL, Thread.currentThread().getId()))
                    .build());

            eventStorageProvider.store(Event
                    .builder()
                    .action(EventType.COURIER_DELIVERY_ORDER)
                    .date(System.currentTimeMillis())
                    .order(order)
                    .creator(String.format(CREATOR_TPL, Thread.currentThread().getId()))
                    .build());

        } else {
            eventStorageProvider.store(Event
                    .builder()
                    .action(EventType.COURIER_CANCEL_ORDER)
                    .date(System.currentTimeMillis())
                    .order(order)
                    .creator(String.format(CREATOR_TPL, Thread.currentThread().getId()))
                    .reason("order was discarded and not found from shelves in room.")
                    .build());
        }

    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {
        if(ordersProvider.getOrders().isEmpty() && this.countDownLatchProvider.getLatch().getCount() == 1){
            this.countDownLatchProvider.getLatch().countDown();
        }
    }
}
