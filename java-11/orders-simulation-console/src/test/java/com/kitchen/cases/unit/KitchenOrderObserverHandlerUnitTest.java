package com.kitchen.cases.unit;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.kitchen.core.entity.Shelf;
import com.kitchen.core.entity.Temperature;
import com.kitchen.core.handler.KitchenOrderObserverHandler;
import com.kitchen.dataproviders.EventStorageProvider;
import com.kitchen.dataproviders.ShelvesRoomProvider;
import com.kitchen.dataproviders.dto.Event;
import com.kitchen.dataproviders.dto.EventType;
import com.kitchen.dataproviders.dto.Order;
import com.kitchen.dataproviders.dto.ShelvesRoom;
import com.kitchen.mock.bind.MockKitchenOrderObserverHandlerModule;
import com.kitchen.mock.data.MockUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

@ExtendWith(MockitoExtension.class)
public class KitchenOrderObserverHandlerUnitTest {

    private Injector injector;
    private KitchenOrderObserverHandler handler;
    private ShelvesRoom shelvesRoom;
    private ConcurrentMap<Temperature, Shelf> temperatureShelfMap;
    private ConcurrentMap<Shelf, List<Order>> shelfOrdersMap;
    private ConcurrentMap<Order, Shelf> orderShelfMap;
    private EventStorageProvider eventStorageProvider;

    @BeforeEach
    public void setUp() {
        injector = Guice.createInjector(new MockKitchenOrderObserverHandlerModule());
        handler = injector.getInstance(KitchenOrderObserverHandler.class);
        shelvesRoom = injector.getInstance(ShelvesRoomProvider.class).getShelvesRoom();
        temperatureShelfMap = shelvesRoom.getTemperatureShelfMap();
        shelfOrdersMap = shelvesRoom.getShelfOrdersMap();
        orderShelfMap = shelvesRoom.getOrderShelvesMap();
        eventStorageProvider = injector.getInstance(EventStorageProvider.class);
        eventStorageProvider.clear();
    }

    @AfterEach
    public void tearDown() {
        shelvesRoom.clear();
        eventStorageProvider.clear();
    }

    @Test
    public void acceptOrder_PutOrderInShelf(){
        Order order = MockUtils.mockObjectFromFile("data/usercase/kitchen/cold_order.json", Order.class);
        order.setStartTimestamp(Instant.now().plusSeconds(3600).toEpochMilli());
        Temperature temperature = Temperature.fromText(order.getTemp());
        handler.onNext(order);

        Assertions.assertEquals(1, shelfOrdersMap.get(temperatureShelfMap.get(temperature)).size());
        Assertions.assertEquals(order.getId(), shelfOrdersMap.get(temperatureShelfMap.get(temperature)).get(0).getId());
        Assertions.assertEquals(temperatureShelfMap.get(temperature).getName(), orderShelfMap.get(order).getName());

        List<Event> events = eventStorageProvider.getALl();

        Assertions.assertNotNull(events);
        Assertions.assertEquals(3, events.size());
        Assertions.assertEquals(1, events.stream().filter(x->x.getOrder().getId().compareToIgnoreCase(order.getId()) == 0 && x.getAction() == EventType.KITCHEN_ACCEPT_ORDER).count());
        Assertions.assertEquals(1, events.stream().filter(x->x.getOrder().getId().compareToIgnoreCase(order.getId()) == 0 && x.getAction() == EventType.KITCHEN_COOKED_ORDER).count());
        Assertions.assertEquals(1, events.stream().filter(x->x.getOrder().getId().compareToIgnoreCase(order.getId()) == 0 && x.getAction() == EventType.KITCHEN_PUT_ORDER_TO_SHELVES).count());
    }
}
