package com.kitchen.dataproviders.impl;

import com.kitchen.dataproviders.ShelvesRoomProvider;
import com.kitchen.dataproviders.dto.ShelvesRoom;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShelvesRoomProviderImpl implements ShelvesRoomProvider {

    @Override
    public ShelvesRoom getShelvesRoom() {
        return ShelvesRoom.getInstance();
    }
}
