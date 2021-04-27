package com.kitchen.mock.bind;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.kitchen.core.usercase.CourierPickupOrderFromShelvesRoom;
import com.kitchen.core.usercase.impl.CourierPickupOrderFromShelvesRoomImpl;
import com.kitchen.dataproviders.ShelvesRoomProvider;
import com.kitchen.dataproviders.impl.ShelvesRoomProviderImpl;

public class MockCourierPickupOrderFromShelvesRoomModule extends AbstractModule {

    @Override
    protected void configure() {

        this.bind(ShelvesRoomProvider.class).to(ShelvesRoomProviderImpl.class).in(Scopes.SINGLETON);

        this.bind(CourierPickupOrderFromShelvesRoom.class).to(CourierPickupOrderFromShelvesRoomImpl.class).in(Scopes.SINGLETON);
    }
}
