package com.kitchen.cases.unit;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.kitchen.core.entity.Shelf;
import com.kitchen.core.entity.Temperature;
import com.kitchen.core.usercase.CourierPickupOrderFromShelvesRoom;
import com.kitchen.dataproviders.ShelvesRoomProvider;
import com.kitchen.dataproviders.dto.Order;
import com.kitchen.dataproviders.dto.ShelvesRoom;
import com.kitchen.mock.bind.MockCourierPickupOrderFromShelvesRoomModule;
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
public class CourierPickupOrderFromShelvesRoomUserCaseUnitTest {

    private Injector injector;
    private CourierPickupOrderFromShelvesRoom usercase;
    private ShelvesRoom shelvesRoom;
    private ConcurrentMap<Temperature, Shelf> temperatureShelfMap;
    private ConcurrentMap<Shelf, List<Order>> shelfOrdersMap;
    private ConcurrentMap<Order, Shelf> orderShelfMap;

    @BeforeEach
    public void setUp() {
        injector = Guice.createInjector(new MockCourierPickupOrderFromShelvesRoomModule());
        usercase = injector.getInstance(CourierPickupOrderFromShelvesRoom.class);
        shelvesRoom = injector.getInstance(ShelvesRoomProvider.class).getShelvesRoom();
        temperatureShelfMap = shelvesRoom.getTemperatureShelfMap();
        shelfOrdersMap = shelvesRoom.getShelfOrdersMap();
        orderShelfMap = shelvesRoom.getOrderShelvesMap();
    }

    @AfterEach
    public void tearDown() {
        shelvesRoom.clear();
    }

    @Test
    public void givenOrderInShelf_PickupOrderSuccessFromShelvesRoom() {
        Order order = MockUtils.mockObjectFromFile("data/usercase/courier/pickup_order.json", Order.class);
        order.setStartTimestamp(Instant.now().plusSeconds(3600).toEpochMilli());
        Temperature temperature = Temperature.fromText(order.getTemp());

        shelvesRoom.putOrderIntoShelves(order);
        Assertions.assertEquals(Temperature.FROZEN, temperature);
        Shelf shelf = temperatureShelfMap.get(temperature);
        List<Order> orders = shelfOrdersMap.get(shelf);
        Assertions.assertEquals(1, orders.size());
        Assertions.assertNotNull(shelfOrdersMap.get(shelf));

        Order pickUp = usercase.execute(order);
        Assertions.assertEquals(order.getId(), pickUp.getId());
        Assertions.assertEquals(0, orders.size());
        Assertions.assertNull(orderShelfMap.get(order));
    }

    @Test
    public void givenOrderInShelf_PickupOrderNotFoundFromShelvesRoom() {
        Order order = MockUtils.mockObjectFromFile("data/usercase/courier/pickup_order.json", Order.class);
        order.setStartTimestamp(Instant.now().toEpochMilli());
        Temperature temperature = Temperature.fromText(order.getTemp());

        shelvesRoom.putOrderIntoShelves(order);
        Assertions.assertEquals(Temperature.FROZEN, temperature);
        Shelf shelf = temperatureShelfMap.get(temperature);
        List<Order> orders = shelfOrdersMap.get(shelf);
        Assertions.assertEquals(1, orders.size());
        Assertions.assertNotNull(shelfOrdersMap.get(shelf));
        order.setStartTimestamp(Instant.ofEpochMilli(order.getStartTimestamp()).minusSeconds(3600).toEpochMilli());

        Order pickUp = usercase.execute(order);
        Assertions.assertNull(pickUp);
        Assertions.assertEquals(0, orders.size());
        Assertions.assertNull(orderShelfMap.get(order));
    }
}
