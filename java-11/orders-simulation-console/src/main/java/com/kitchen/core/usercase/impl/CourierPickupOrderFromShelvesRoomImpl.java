package com.kitchen.core.usercase.impl;

import com.kitchen.core.usercase.CourierPickupOrderFromShelvesRoom;
import com.kitchen.dataproviders.ShelvesRoomProvider;
import com.kitchen.dataproviders.dto.Order;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.util.Objects;

@Slf4j
public class CourierPickupOrderFromShelvesRoomImpl implements CourierPickupOrderFromShelvesRoom {

    private final ShelvesRoomProvider shelvesRoomProvider;

    @Inject
    public CourierPickupOrderFromShelvesRoomImpl(ShelvesRoomProvider shelvesRoomProvider) {
        Objects.requireNonNull(shelvesRoomProvider, "shelvesRoomProvider is required");
        this.shelvesRoomProvider = shelvesRoomProvider;
    }

    @Override
    public Order execute(Order order) {
        return shelvesRoomProvider
                .getShelvesRoom()
                .pickUpOrderFromShelves(order);
    }
}
