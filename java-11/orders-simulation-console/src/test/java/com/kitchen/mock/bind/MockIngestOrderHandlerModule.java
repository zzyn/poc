package com.kitchen.mock.bind;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.kitchen.core.handler.AppExitHandler;
import com.kitchen.core.handler.CourierOrderObserverHandler;
import com.kitchen.core.handler.IngestOrderHandler;
import com.kitchen.core.handler.KitchenOrderObserverHandler;
import com.kitchen.core.handler.impl.CourierOrderObserverHandlerImpl;
import com.kitchen.core.handler.impl.IngestOrderHandlerImpl;
import com.kitchen.core.handler.impl.KitchenOrderObserverHandlerImpl;
import com.kitchen.core.usercase.CourierPickupOrderFromShelvesRoom;
import com.kitchen.core.usercase.KitchenPutOrderInShelvesRoom;
import com.kitchen.core.usercase.impl.CourierPickupOrderFromShelvesRoomImpl;
import com.kitchen.core.usercase.impl.KitchenPutOrderInShelvesRoomImpl;
import com.kitchen.dataproviders.*;
import com.kitchen.dataproviders.impl.CountDownLatchProviderImpl;
import com.kitchen.dataproviders.impl.EventStorageProviderImpl;
import com.kitchen.dataproviders.impl.ShelvesRoomProviderImpl;
import com.kitchen.mock.impl.MockAppExitHandlerImpl;

public class MockIngestOrderHandlerModule extends AbstractModule {

    private final OrdersProvider ordersProvider;
    private final IngestRateProvider ingestRateProvider;

    public MockIngestOrderHandlerModule(OrdersProvider ordersProvider, IngestRateProvider ingestRateProvider) {
        this.ordersProvider = ordersProvider;
        this.ingestRateProvider = ingestRateProvider;
    }

    @Override
    protected void configure() {

        //external
        this.bind(AppExitHandler.class).to(MockAppExitHandlerImpl.class).in(Scopes.SINGLETON);
        this.bind(OrdersProvider.class).toInstance(this.ordersProvider);
        this.bind(IngestRateProvider.class).toInstance(this.ingestRateProvider);

        //provider
        this.bind(EventStorageProvider.class).to(EventStorageProviderImpl.class).in(Scopes.SINGLETON);
        this.bind(ShelvesRoomProvider.class).to(ShelvesRoomProviderImpl.class).in(Scopes.SINGLETON);
        this.bind(CountDownLatchProvider.class).to(CountDownLatchProviderImpl.class).in(Scopes.SINGLETON);

        //user case
        this.bind(CourierPickupOrderFromShelvesRoom.class).to(CourierPickupOrderFromShelvesRoomImpl.class).in(Scopes.SINGLETON);
        this.bind(KitchenPutOrderInShelvesRoom.class).to(KitchenPutOrderInShelvesRoomImpl.class).in(Scopes.SINGLETON);

        //handler
        this.bind(KitchenOrderObserverHandler.class).to(KitchenOrderObserverHandlerImpl.class).in(Scopes.SINGLETON);
        this.bind(CourierOrderObserverHandler.class).to(CourierOrderObserverHandlerImpl.class).in(Scopes.SINGLETON);
        this.bind(IngestOrderHandler.class).to(IngestOrderHandlerImpl.class).in(Scopes.SINGLETON);

    }
}
