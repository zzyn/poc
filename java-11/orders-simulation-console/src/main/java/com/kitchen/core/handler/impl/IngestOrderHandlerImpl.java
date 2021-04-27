package com.kitchen.core.handler.impl;

import com.kitchen.core.handler.AppExitHandler;
import com.kitchen.core.handler.CourierOrderObserverHandler;
import com.kitchen.core.handler.IngestOrderHandler;
import com.kitchen.core.handler.KitchenOrderObserverHandler;
import com.kitchen.dataproviders.CountDownLatchProvider;
import com.kitchen.dataproviders.EventStorageProvider;
import com.kitchen.dataproviders.IngestRateProvider;
import com.kitchen.dataproviders.OrdersProvider;
import com.kitchen.dataproviders.dto.Event;
import com.kitchen.dataproviders.dto.EventType;
import com.kitchen.dataproviders.dto.Order;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class IngestOrderHandlerImpl extends TimerTask implements IngestOrderHandler {

    private final OrdersProvider ordersProvider;
    private final KitchenOrderObserverHandler kitchenOrderObserverHandler;
    private final CourierOrderObserverHandler courierOrderObserverHandler;
    private final IngestRateProvider ingestRateProvider;
    private final EventStorageProvider eventStorageProvider;
    private final AppExitHandler appExitHandler;
    private final CountDownLatchProvider countDownLatchProvider;
    private Queue<Order> queue;

    @Inject
    public IngestOrderHandlerImpl(OrdersProvider ordersProvider,
                                  IngestRateProvider ingestRateProvider,
                                  EventStorageProvider eventStorageProvider,
                                  KitchenOrderObserverHandler kitchenOrderObserverHandler,
                                  CourierOrderObserverHandler courierOrderObserverHandler,
                                  AppExitHandler appExitHandler, CountDownLatchProvider countDownLatchProvider) {


        Objects.requireNonNull(ordersProvider, "ordersProvider is required");
        Objects.requireNonNull(ingestRateProvider, "ingestRateProvider is required");
        Objects.requireNonNull(eventStorageProvider, "eventStorageProvider is required");
        Objects.requireNonNull(courierOrderObserverHandler, "courierOrderObserverHandler is required");
        Objects.requireNonNull(kitchenOrderObserverHandler, "kitchenOrderObserverHandler is required");
        Objects.requireNonNull(countDownLatchProvider, "countDownLatchProvider is required");
        Objects.requireNonNull(appExitHandler, "appExitHandler is required");
        this.ordersProvider = ordersProvider;
        this.ingestRateProvider = ingestRateProvider;
        this.eventStorageProvider = eventStorageProvider;
        this.courierOrderObserverHandler = courierOrderObserverHandler;
        this.kitchenOrderObserverHandler = kitchenOrderObserverHandler;
        this.appExitHandler = appExitHandler;
        this.countDownLatchProvider = countDownLatchProvider;
    }

    @SneakyThrows
    @Override
    public void run() {

        queue = ordersProvider.getOrders();

        if (queue.isEmpty()) {
            log.warn("app is terminated");
            cancel();
            appExitHandler.exit();
        }

        AtomicReference<List<Order>> orders = new AtomicReference<>(new ArrayList<>());
        AtomicInteger times = new AtomicInteger(ingestRateProvider.getIngestRate());
        long orderStarDate = System.currentTimeMillis();
        while (times.get() > 0) {
            if (!queue.isEmpty()) {
                Order order = queue.poll();
                order.setStartTimestamp(orderStarDate);
                eventStorageProvider.store(Event
                        .builder()
                        .action(EventType.INGEST_ORDER)
                        .date(order.getStartTimestamp())
                        .order(order)
                        .creator("App")
                        .build());

                orders.get().add(order);
            }
            times.decrementAndGet();
        }


        Observable.fromIterable(orders.get())
                .observeOn(Schedulers.io())
                .subscribe(kitchenOrderObserverHandler);

        Observable.fromIterable(orders.get())
                .observeOn(Schedulers.io())
                .delay(ThreadLocalRandom.current().nextInt(2, 6), TimeUnit.SECONDS)
                .subscribe(courierOrderObserverHandler);
    }
}
