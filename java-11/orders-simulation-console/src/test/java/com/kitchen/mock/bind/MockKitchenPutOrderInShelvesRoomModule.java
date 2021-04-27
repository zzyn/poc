package com.kitchen.mock.bind;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.kitchen.core.usercase.KitchenPutOrderInShelvesRoom;
import com.kitchen.core.usercase.impl.KitchenPutOrderInShelvesRoomImpl;
import com.kitchen.dataproviders.ShelvesRoomProvider;
import com.kitchen.dataproviders.impl.ShelvesRoomProviderImpl;

public class MockKitchenPutOrderInShelvesRoomModule extends AbstractModule {

    @Override
    protected void configure() {

        this.bind(ShelvesRoomProvider.class).to(ShelvesRoomProviderImpl.class).in(Scopes.SINGLETON);

        this.bind(KitchenPutOrderInShelvesRoom.class).to(KitchenPutOrderInShelvesRoomImpl.class).in(Scopes.SINGLETON);
    }
}
