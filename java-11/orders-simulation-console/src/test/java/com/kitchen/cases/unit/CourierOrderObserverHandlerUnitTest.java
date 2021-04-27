package com.kitchen.cases.unit;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.kitchen.core.entity.Shelf;
import com.kitchen.core.entity.Temperature;
import com.kitchen.core.handler.CourierOrderObserverHandler;
import com.kitchen.dataproviders.EventStorageProvider;
import com.kitchen.dataproviders.OrdersProvider;
import com.kitchen.dataproviders.ShelvesRoomProvider;
import com.kitchen.dataproviders.dto.Event;
import com.kitchen.dataproviders.dto.EventType;
import com.kitchen.dataproviders.dto.Order;
import com.kitchen.dataproviders.dto.ShelvesRoom;
import com.kitchen.mock.bind.MockCourierOrderObserverHandlerModule;
import com.kitchen.mock.data.MockUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

@ExtendWith(MockitoExtension.class)
public class CourierOrderObserverHandlerUnitTest {

    private Injector injector;
    private CourierOrderObserverHandler handler;
    private ShelvesRoom shelvesRoom;
    private ConcurrentMap<Temperature, Shelf> temperatureShelfMap;
    private ConcurrentMap<Shelf, List<Order>> shelfOrdersMap;
    private ConcurrentMap<Order, Shelf> orderShelfMap;
    private EventStorageProvider eventStorageProvider;

    @Mock
    private OrdersProvider ordersProvider;

    @BeforeEach
    public void setUp() {
        injector = Guice.createInjector(new MockCourierOrderObserverHandlerModule(ordersProvider));
        handler = injector.getInstance(CourierOrderObserverHandler.class);
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
    public void givenOrderInShelf_CourierDeliverySuccess(){

        Order order = MockUtils.mockObjectFromFile("data/usercase/courier/pickup_order.json", Order.class);
        order.setStartTimestamp(Instant.now().plusSeconds(3600).toEpochMilli());
        Temperature temperature = Temperature.fromText(order.getTemp());
        shelvesRoom.putOrderIntoShelves(order);
        Assertions.assertEquals(Temperature.FROZEN, temperature);
        Shelf shelf = temperatureShelfMap.get(temperature);
        List<Order> orders = shelfOrdersMap.get(shelf);
        Assertions.assertEquals(1, orders.size());
        Assertions.assertNotNull(shelfOrdersMap.get(shelf));

        handler.onNext(order);

        Assertions.assertEquals(0,shelfOrdersMap.get(shelf).size());
        Assertions.assertNull(orderShelfMap.get(order));

        List<Event> events = eventStorageProvider.getALl();

        Assertions.assertNotNull(events);
        Assertions.assertEquals(3, events.size());
        Assertions.assertEquals(1, events.stream().filter(x->x.getOrder().getId().compareToIgnoreCase(order.getId()) == 0 && x.getAction() == EventType.COURIER_ACCEPT_ORDER).count());
        Assertions.assertEquals(1, events.stream().filter(x->x.getOrder().getId().compareToIgnoreCase(order.getId()) == 0 && x.getAction() == EventType.COURIER_PICKUP_ORDER).count());
        Assertions.assertEquals(1, events.stream().filter(x->x.getOrder().getId().compareToIgnoreCase(order.getId()) == 0 && x.getAction() == EventType.COURIER_DELIVERY_ORDER).count());
    }

    @Test
    public void givenOrderInShelf_CourierCancelSuccess(){

        Order order = MockUtils.mockObjectFromFile("data/usercase/courier/pickup_order.json", Order.class);
        order.setStartTimestamp(Instant.now().plusSeconds(3600).toEpochMilli());
        handler.onNext(order);

        List<Event> events = eventStorageProvider.getALl();

        Assertions.assertNotNull(events);
        Assertions.assertEquals(2, events.size());
        Assertions.assertEquals(1, events.stream().filter(x->x.getOrder().getId().compareToIgnoreCase(order.getId()) == 0 && x.getAction() == EventType.COURIER_ACCEPT_ORDER).count());
        Assertions.assertEquals(1, events.stream().filter(x->x.getOrder().getId().compareToIgnoreCase(order.getId()) == 0 && x.getAction() == EventType.COURIER_CANCEL_ORDER).count());
    }
}
