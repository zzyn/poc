package com.kitchen.core.usercase.impl;

import com.kitchen.core.usercase.KitchenPutOrderInShelvesRoom;
import com.kitchen.dataproviders.ShelvesRoomProvider;
import com.kitchen.dataproviders.dto.Order;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.util.Objects;

@Slf4j
public class KitchenPutOrderInShelvesRoomImpl implements KitchenPutOrderInShelvesRoom {

    private final ShelvesRoomProvider shelvesRoomProvider;

    @Inject
    public KitchenPutOrderInShelvesRoomImpl(ShelvesRoomProvider shelvesRoomProvider) {
        Objects.requireNonNull(shelvesRoomProvider, "shelvesRoomProvider is required");
        this.shelvesRoomProvider = shelvesRoomProvider;
    }

    @Override
    public void execute(Order order) {
        this.shelvesRoomProvider
                .getShelvesRoom()
                .putOrderIntoShelves(order);
    }
}
