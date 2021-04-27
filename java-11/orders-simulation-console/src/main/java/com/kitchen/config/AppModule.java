package com.kitchen.config;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.kitchen.core.handler.AppExitHandler;
import com.kitchen.core.handler.CourierOrderObserverHandler;
import com.kitchen.core.handler.IngestOrderHandler;
import com.kitchen.core.handler.KitchenOrderObserverHandler;
import com.kitchen.core.handler.impl.AppExitHandlerImpl;
import com.kitchen.core.handler.impl.CourierOrderObserverHandlerImpl;
import com.kitchen.core.handler.impl.IngestOrderHandlerImpl;
import com.kitchen.core.handler.impl.KitchenOrderObserverHandlerImpl;
import com.kitchen.core.usercase.CourierPickupOrderFromShelvesRoom;
import com.kitchen.core.usercase.KitchenPutOrderInShelvesRoom;
import com.kitchen.core.usercase.impl.CourierPickupOrderFromShelvesRoomImpl;
import com.kitchen.core.usercase.impl.KitchenPutOrderInShelvesRoomImpl;
import com.kitchen.dataproviders.*;
import com.kitchen.dataproviders.impl.*;

public class AppModule extends AbstractModule {

    @Override
    protected void configure() {

        //provider
        this.bind(OrdersProvider.class).to(OrdersProviderJsonFileImpl.class).in(Scopes.SINGLETON);
        this.bind(IngestRateProvider.class).to(IngestRateProviderConfigImpl.class).in(Scopes.SINGLETON);
        this.bind(EventStorageProvider.class).to(EventStorageProviderImpl.class).in(Scopes.SINGLETON);
        this.bind(ShelvesRoomProvider.class).to(ShelvesRoomProviderImpl.class).in(Scopes.SINGLETON);
        this.bind(CountDownLatchProvider.class).to(CountDownLatchProviderImpl.class).in(Scopes.SINGLETON);

        //handler
        this.bind(AppExitHandler.class).to(AppExitHandlerImpl.class).in(Scopes.SINGLETON);
        this.bind(IngestOrderHandler.class).to(IngestOrderHandlerImpl.class).in(Scopes.SINGLETON);
        this.bind(KitchenOrderObserverHandler.class).to(KitchenOrderObserverHandlerImpl.class).in(Scopes.SINGLETON);
        this.bind(CourierOrderObserverHandler.class).to(CourierOrderObserverHandlerImpl.class).in(Scopes.SINGLETON);

        //user case
        this.bind(CourierPickupOrderFromShelvesRoom.class).to(CourierPickupOrderFromShelvesRoomImpl.class).in(Scopes.SINGLETON);
        this.bind(KitchenPutOrderInShelvesRoom.class).to(KitchenPutOrderInShelvesRoomImpl.class).in(Scopes.SINGLETON);
    }
}
