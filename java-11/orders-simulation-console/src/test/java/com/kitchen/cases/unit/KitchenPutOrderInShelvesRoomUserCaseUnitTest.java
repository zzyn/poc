package com.kitchen.cases.unit;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.kitchen.core.entity.Shelf;
import com.kitchen.core.entity.Temperature;
import com.kitchen.core.usercase.KitchenPutOrderInShelvesRoom;
import com.kitchen.dataproviders.ShelvesRoomProvider;
import com.kitchen.dataproviders.dto.Order;
import com.kitchen.dataproviders.dto.ShelvesRoom;
import com.kitchen.mock.bind.MockKitchenPutOrderInShelvesRoomModule;
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
public class KitchenPutOrderInShelvesRoomUserCaseUnitTest {

    private Injector injector;
    private KitchenPutOrderInShelvesRoom usercase;
    private ShelvesRoom shelvesRoom;
    private ConcurrentMap<Temperature, Shelf> temperatureShelfMap;
    private ConcurrentMap<Shelf, List<Order>> shelfOrdersMap;
    private ConcurrentMap<Order, Shelf> orderShelfMap;

    @BeforeEach
    public void setUp() {
        injector = Guice.createInjector(new MockKitchenPutOrderInShelvesRoomModule());
        usercase = injector.getInstance(KitchenPutOrderInShelvesRoom.class);
        shelvesRoom = injector.getInstance(ShelvesRoomProvider.class).getShelvesRoom();
        temperatureShelfMap = shelvesRoom.getTemperatureShelfMap();
        shelfOrdersMap = shelvesRoom.getShelfOrdersMap();
        orderShelfMap = shelvesRoom.getOrderShelvesMap();
        shelvesRoom.clear();
    }

    @AfterEach
    public void tearDown() {
        shelvesRoom.clear();
    }

    @Test
    public void acceptOrder_whenSingleTemperatureShelfIsNotFull_putOrderInSingleTemperatureShelf() {
        Order order = MockUtils.mockObjectFromFile("data/usercase/kitchen/cold_order.json", Order.class);
        order.setStartTimestamp(Instant.now().plusSeconds(3600).toEpochMilli());
        Temperature temperature = Temperature.fromText(order.getTemp());

        usercase.execute(order);
        Assertions.assertEquals(1, shelfOrdersMap.get(temperatureShelfMap.get(temperature)).size());
        Assertions.assertEquals(order.getId(), shelfOrdersMap.get(temperatureShelfMap.get(temperature)).get(0).getId());
        Assertions.assertEquals(temperatureShelfMap.get(temperature).getName(), orderShelfMap.get(order).getName());
    }

    @Test
    public void acceptOrder_whenSingleTemperatureShelfIsAlwaysFullAndOverflowShelfIsNotFull_putOrderInOverflowShelf() {

        List<Order> singleShelfOrders = MockUtils.mockListFromFile("data/usercase/kitchen/orders_coldshelf_alwaysfull.json", Order.class);
        singleShelfOrders.forEach(x -> {
            x.setStartTimestamp(Instant.now().plusSeconds(3600).toEpochMilli());
            Temperature temp = Temperature.fromText(x.getTemp());
            Shelf shelf = temperatureShelfMap.get(temp);
            List<Order> orders = shelfOrdersMap.get(shelf);
            orders.add(x);
            orderShelfMap.put(x, shelf);
        });

        Order order = MockUtils.mockObjectFromFile("data/usercase/kitchen/cold_order.json", Order.class);
        order.setStartTimestamp(Instant.now().plusSeconds(3600).toEpochMilli());
        Temperature temperature = Temperature.fromText(order.getTemp());

        usercase.execute(order);
        Assertions.assertEquals(temperatureShelfMap.get(temperature).getCapacity(), shelfOrdersMap.get(temperatureShelfMap.get(temperature)).size());
        Assertions.assertEquals(1, shelfOrdersMap.get(temperatureShelfMap.get(Temperature.ANY)).size());
        Assertions.assertEquals(order.getId(), shelfOrdersMap.get(temperatureShelfMap.get(Temperature.ANY)).get(0).getId());
        Assertions.assertEquals(temperatureShelfMap.get(Temperature.ANY).getName(), orderShelfMap.get(order).getName());

    }

    @Test
    public void acceptOrder_whenSingleTemperatureShelfIsAlwaysFullAndOverflowShelfIsAlwaysFull_randomRemoveAndPutOrderInOverflowShelf() {

        List<Order> singleShelfOrders = MockUtils.mockListFromFile("data/usercase/kitchen/orders_coldshelf_alwaysfull.json", Order.class);
        singleShelfOrders.forEach(x -> {
            x.setStartTimestamp(Instant.now().plusSeconds(3600).toEpochMilli());
            Temperature temp = Temperature.fromText(x.getTemp());
            Shelf shelf = temperatureShelfMap.get(temp);
            List<Order> orders = shelfOrdersMap.get(shelf);
            orders.add(x);
            orderShelfMap.put(x, shelf);
        });

        List<Order> overflowShelfOrders = MockUtils.mockListFromFile("data/usercase/kitchen/orders_overflowshelf_alwaysfull_allcold.json", Order.class);
        overflowShelfOrders.forEach(x -> {
            x.setStartTimestamp(Instant.now().plusSeconds(3600).toEpochMilli());
            Shelf shelf = temperatureShelfMap.get(Temperature.ANY);
            List<Order> orders = shelfOrdersMap.get(shelf);
            orders.add(x);
            orderShelfMap.put(x, shelf);
        });

        Order order = MockUtils.mockObjectFromFile("data/usercase/kitchen/cold_order.json", Order.class);
        order.setStartTimestamp(Instant.now().plusSeconds(3600).toEpochMilli());
        Temperature temperature = Temperature.fromText(order.getTemp());

        usercase.execute(order);
        Assertions.assertEquals(temperatureShelfMap.get(temperature).getCapacity(), shelfOrdersMap.get(temperatureShelfMap.get(temperature)).size());
        Assertions.assertEquals(temperatureShelfMap.get(Temperature.ANY).getCapacity(), shelfOrdersMap.get(temperatureShelfMap.get(Temperature.ANY)).size());
        Assertions.assertEquals(1, shelfOrdersMap.get(temperatureShelfMap.get(Temperature.ANY)).stream().filter(x -> x.getId().compareToIgnoreCase(order.getId()) == 0).count());
        Assertions.assertEquals(temperatureShelfMap.get(Temperature.ANY).getName(), orderShelfMap.get(order).getName());

    }

    @Test
    public void acceptOrder_whenSingleTemperatureShelfIsTempFullAndOverflowShelfIsNotFull_putOrderInSingleTemperatureShelf() {

        Order order = MockUtils.mockObjectFromFile("data/usercase/kitchen/cold_order.json", Order.class);
        order.setStartTimestamp(Instant.now().plusSeconds(3600).toEpochMilli());
        Temperature temperature = Temperature.fromText(order.getTemp());

        List<Order> singleShelfOrders = MockUtils.mockListFromFile("data/usercase/kitchen/orders_coldshelf_alwaysfull.json", Order.class);
        singleShelfOrders.forEach(x -> {
            x.setStartTimestamp(Instant.now().plusSeconds(3600).toEpochMilli());
            Temperature temp = Temperature.fromText(x.getTemp());
            Shelf shelf = temperatureShelfMap.get(temp);
            List<Order> orders = shelfOrdersMap.get(shelf);
            orders.add(x);
            orderShelfMap.put(x, shelf);
        });

        Assertions.assertEquals(temperatureShelfMap.get(temperature).getCapacity(), shelfOrdersMap.get(temperatureShelfMap.get(temperature)).size());
        shelfOrdersMap.get(temperatureShelfMap.get(temperature)).get(0).setStartTimestamp(Instant.now().minusSeconds(7200).toEpochMilli());

        usercase.execute(order);
        Assertions.assertEquals(temperatureShelfMap.get(temperature).getCapacity(), shelfOrdersMap.get(temperatureShelfMap.get(temperature)).size());
        Assertions.assertEquals(1, shelfOrdersMap.get(temperatureShelfMap.get(temperature)).stream().filter(x-> x.getId().compareToIgnoreCase(order.getId()) == 0).count());
        Assertions.assertEquals(temperatureShelfMap.get(temperature).getName(), orderShelfMap.get(order).getName());
    }

    @Test
    public void acceptOrder_whenSingleTemperatureShelfIsAlwaysFullAndOverflowShelfIsTempFull_PutOrderInOverflowShelf() {

        List<Order> singleShelfOrders = MockUtils.mockListFromFile("data/usercase/kitchen/orders_coldshelf_alwaysfull.json", Order.class);
        singleShelfOrders.forEach(x -> {
            x.setStartTimestamp(Instant.now().plusSeconds(3600).toEpochMilli());
            Temperature temp = Temperature.fromText(x.getTemp());
            Shelf shelf = temperatureShelfMap.get(temp);
            List<Order> orders = shelfOrdersMap.get(shelf);
            orders.add(x);
            orderShelfMap.put(x, shelf);
        });

        List<Order> overflowShelfOrders = MockUtils.mockListFromFile("data/usercase/kitchen/orders_overflowshelf_alwaysfull_allcold.json", Order.class);
        overflowShelfOrders.forEach(x -> {
            x.setStartTimestamp(Instant.now().plusSeconds(3600).toEpochMilli());
            Shelf shelf = temperatureShelfMap.get(Temperature.ANY);
            List<Order> orders = shelfOrdersMap.get(shelf);
            orders.add(x);
            orderShelfMap.put(x, shelf);
        });

        Assertions.assertEquals(temperatureShelfMap.get(Temperature.ANY).getCapacity(), shelfOrdersMap.get(temperatureShelfMap.get(Temperature.ANY)).size());
        shelfOrdersMap.get(temperatureShelfMap.get(Temperature.ANY)).get(0).setStartTimestamp(Instant.now().minusSeconds(7200).toEpochMilli());

        Order order = MockUtils.mockObjectFromFile("data/usercase/kitchen/cold_order.json", Order.class);
        order.setStartTimestamp(Instant.now().plusSeconds(3600).toEpochMilli());
        Temperature temperature = Temperature.fromText(order.getTemp());

        usercase.execute(order);
        Assertions.assertEquals(temperatureShelfMap.get(temperature).getCapacity(), shelfOrdersMap.get(temperatureShelfMap.get(temperature)).size());
        Assertions.assertEquals(temperatureShelfMap.get(Temperature.ANY).getCapacity(), shelfOrdersMap.get(temperatureShelfMap.get(Temperature.ANY)).size());
        Assertions.assertEquals(temperatureShelfMap.get(Temperature.ANY).getName(), orderShelfMap.get(order).getName());
        Assertions.assertEquals(1, shelfOrdersMap.get(temperatureShelfMap.get(Temperature.ANY)).stream().filter(x -> x.getId().compareToIgnoreCase(order.getId()) == 0).count());
    }

    @Test
    public void acceptOrder_whenSingleTemperatureShelfIsAlwaysFullAndOverflowShelfIsAlwaysMixedFull_PutOrderInOverflowShelf() {

        List<Order> singleShelfOrders = MockUtils.mockListFromFile("data/usercase/kitchen/orders_coldshelf_alwaysfull.json", Order.class);
        singleShelfOrders.forEach(x -> {
            x.setStartTimestamp(Instant.now().plusSeconds(3600).toEpochMilli());
            Temperature temp = Temperature.fromText(x.getTemp());
            Shelf shelf = temperatureShelfMap.get(temp);
            List<Order> orders = shelfOrdersMap.get(shelf);
            orders.add(x);
            orderShelfMap.put(x, shelf);
        });

        List<Order> overflowShelfOrders = MockUtils.mockListFromFile("data/usercase/kitchen/orders_overflowshelf_alwaysfull_mixed.json", Order.class);
        overflowShelfOrders.forEach(x -> {
            x.setStartTimestamp(Instant.now().plusSeconds(3600).toEpochMilli());
            Shelf shelf = temperatureShelfMap.get(Temperature.ANY);
            List<Order> orders = shelfOrdersMap.get(shelf);
            orders.add(x);
            orderShelfMap.put(x, shelf);
        });

        Order last = overflowShelfOrders.get(overflowShelfOrders.size() - 1);
        Temperature lastTemperature = Temperature.fromText(last.getTemp());

        Order order = MockUtils.mockObjectFromFile("data/usercase/kitchen/cold_order.json", Order.class);
        order.setStartTimestamp(Instant.now().plusSeconds(3600).toEpochMilli());
        Temperature temperature = Temperature.fromText(order.getTemp());

        usercase.execute(order);
        Assertions.assertEquals(temperatureShelfMap.get(temperature).getCapacity(), shelfOrdersMap.get(temperatureShelfMap.get(temperature)).size());
        Assertions.assertEquals(temperatureShelfMap.get(Temperature.ANY).getCapacity(), shelfOrdersMap.get(temperatureShelfMap.get(Temperature.ANY)).size());
        Assertions.assertEquals(temperatureShelfMap.get(Temperature.ANY).getName(), orderShelfMap.get(order).getName());
        Assertions.assertEquals(1, shelfOrdersMap.get(temperatureShelfMap.get(Temperature.ANY)).stream().filter(x -> x.getId().compareToIgnoreCase(order.getId()) == 0).count());

        Assertions.assertEquals(1, shelfOrdersMap.get(temperatureShelfMap.get(lastTemperature)).size());
        Assertions.assertEquals(last.getId(), shelfOrdersMap.get(temperatureShelfMap.get(lastTemperature)).get(0).getId());
        Assertions.assertEquals(temperatureShelfMap.get(lastTemperature).getName(), orderShelfMap.get(last).getName());
    }
}
