package com.kitchen.mock.bind;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.kitchen.core.handler.CourierOrderObserverHandler;
import com.kitchen.core.handler.impl.CourierOrderObserverHandlerImpl;
import com.kitchen.core.usercase.CourierPickupOrderFromShelvesRoom;
import com.kitchen.core.usercase.impl.CourierPickupOrderFromShelvesRoomImpl;
import com.kitchen.dataproviders.CountDownLatchProvider;
import com.kitchen.dataproviders.EventStorageProvider;
import com.kitchen.dataproviders.OrdersProvider;
import com.kitchen.dataproviders.ShelvesRoomProvider;
import com.kitchen.dataproviders.impl.CountDownLatchProviderImpl;
import com.kitchen.dataproviders.impl.EventStorageProviderImpl;
import com.kitchen.dataproviders.impl.ShelvesRoomProviderImpl;

public class MockCourierOrderObserverHandlerModule extends AbstractModule {

    private final OrdersProvider ordersProvider;

    public MockCourierOrderObserverHandlerModule(OrdersProvider ordersProvider) {
        this.ordersProvider = ordersProvider;
    }

    @Override
    protected void configure() {

        this.bind(OrdersProvider.class).toInstance(ordersProvider);

        this.bind(CountDownLatchProvider.class).to(CountDownLatchProviderImpl.class).in(Scopes.SINGLETON);

        this.bind(EventStorageProvider.class).to(EventStorageProviderImpl.class).in(Scopes.SINGLETON);

        this.bind(ShelvesRoomProvider.class).to(ShelvesRoomProviderImpl.class).in(Scopes.SINGLETON);

        this.bind(CourierOrderObserverHandler.class).to(CourierOrderObserverHandlerImpl.class).in(Scopes.SINGLETON);

        this.bind(CourierPickupOrderFromShelvesRoom.class).to(CourierPickupOrderFromShelvesRoomImpl.class).in(Scopes.SINGLETON);
    }
}
