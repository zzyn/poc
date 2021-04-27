package com.kitchen.cases.unit;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.kitchen.core.handler.AppExitHandler;
import com.kitchen.core.handler.IngestOrderHandler;
import com.kitchen.dataproviders.EventStorageProvider;
import com.kitchen.dataproviders.IngestRateProvider;
import com.kitchen.dataproviders.OrdersProvider;
import com.kitchen.dataproviders.ShelvesRoomProvider;
import com.kitchen.dataproviders.dto.Event;
import com.kitchen.dataproviders.dto.EventType;
import com.kitchen.dataproviders.dto.Order;
import com.kitchen.dataproviders.dto.ShelvesRoom;
import com.kitchen.mock.bind.MockIngestOrderHandlerModule;
import com.kitchen.mock.data.MockUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class IngestOrderHandlerUnitTest {

    private Injector injector;

    private IngestOrderHandler handler;

    private EventStorageProvider eventStorageProvider;

    private ShelvesRoom shelvesRoom;

    @Mock
    private OrdersProvider ordersProvider;

    @Mock
    private IngestRateProvider ingestRateProvider;

    @BeforeEach
    public void setUp() {
        injector = Guice.createInjector(new MockIngestOrderHandlerModule(ordersProvider, ingestRateProvider));
        shelvesRoom = injector.getInstance(ShelvesRoomProvider.class).getShelvesRoom();
        handler = injector.getInstance(IngestOrderHandler.class);
        eventStorageProvider = injector.getInstance(EventStorageProvider.class);
        eventStorageProvider.clear();
    }

    @AfterEach
    public void tearDown() {
        shelvesRoom.clear();
        eventStorageProvider.clear();
    }

    @Test
    public void queueIsEmpty_thenExist(){
        Queue<Order> queue = new ConcurrentLinkedDeque<>();
        doReturn(queue)
                .when(ordersProvider)
                .getOrders();

        doReturn(2)
                .when(ingestRateProvider)
                .getIngestRate();

        handler.run();

        List<Event> events = eventStorageProvider.getALl();
        Assertions.assertNotNull(events);
        Assertions.assertEquals(1, events.stream().filter(x-> x.getAction() == EventType.APP_EXIT).count());

    }

    @Test
    public void rateIsTwo_orderSizeSmallerThanRate(){

        List<Order> orders = MockUtils.mockListFromFile("data/handler/ingest/orders_1.json", Order.class);
        Queue<Order> queue = new ConcurrentLinkedDeque<>();
        queue.addAll(orders);

        doReturn(queue)
                .when(ordersProvider)
                .getOrders();

        doReturn(2)
                .when(ingestRateProvider)
                .getIngestRate();

        handler.run();
        List<Event> events = eventStorageProvider.getALl();
        Assertions.assertNotNull(events);
        Assertions.assertEquals(1, events.stream().filter(x-> x.getAction() == EventType.INGEST_ORDER).count());
    }

    @Test
    public void rateIsTwo_orderSizeEqualRate(){

        List<Order> orders = MockUtils.mockListFromFile("data/handler/ingest/orders_2.json", Order.class);
        Queue<Order> queue = new ConcurrentLinkedQueue<>();
        queue.addAll(orders);

        doReturn(queue)
                .when(ordersProvider)
                .getOrders();

        doReturn(2)
                .when(ingestRateProvider)
                .getIngestRate();

        handler.run();
        List<Event> events = eventStorageProvider.getALl();
        Assertions.assertNotNull(events);
        Assertions.assertEquals(2, events.stream().filter(x-> x.getAction() == EventType.INGEST_ORDER).count());
    }

    @Test
    public void rateIsTwo_orderSizeBiggerThanRate(){
        List<Order> orders = MockUtils.mockListFromFile("data/handler/ingest/orders_5.json", Order.class);
        Queue<Order> queue = new ConcurrentLinkedQueue<>();
        queue.addAll(orders);

        doReturn(queue)
                .when(ordersProvider)
                .getOrders();

        doReturn(2)
                .when(ingestRateProvider)
                .getIngestRate();

        handler.run();
        List<Event> events = eventStorageProvider.getALl();
        Assertions.assertNotNull(events);
        Assertions.assertEquals(2, events.stream().filter(x-> x.getAction() == EventType.INGEST_ORDER).count());
    }

    @Test
    public void rateIsThree_orderSizeSmallerThanRate(){

        List<Order> orders = MockUtils.mockListFromFile("data/handler/ingest/orders_1.json", Order.class);
        Queue<Order> queue = new ConcurrentLinkedQueue<>();
        queue.addAll(orders);

        doReturn(queue)
                .when(ordersProvider)
                .getOrders();

        doReturn(3)
                .when(ingestRateProvider)
                .getIngestRate();

        handler.run();
        List<Event> events = eventStorageProvider.getALl();
        Assertions.assertNotNull(events);
        Assertions.assertEquals(1, events.stream().filter(x-> x.getAction() == EventType.INGEST_ORDER).count());
    }

    @Test
    public void rateIsThree_orderSizeEqualRate(){

        List<Order> orders = MockUtils.mockListFromFile("data/handler/ingest/orders_3.json", Order.class);
        Queue<Order> queue = new ConcurrentLinkedQueue<>();
        queue.addAll(orders);

        doReturn(queue)
                .when(ordersProvider)
                .getOrders();

        doReturn(3)
                .when(ingestRateProvider)
                .getIngestRate();

        handler.run();

        List<Event> events = eventStorageProvider.getALl();
        Assertions.assertNotNull(events);
        Assertions.assertEquals(3, events.stream().filter(x-> x.getAction() == EventType.INGEST_ORDER).count());

    }

    @Test
    public void rateIsTree_orderSizeBiggerThanRate(){

        List<Order> orders = MockUtils.mockListFromFile("data/handler/ingest/orders_5.json", Order.class);
        Queue<Order> queue = new ConcurrentLinkedQueue<>();
        queue.addAll(orders);

        doReturn(queue)
                .when(ordersProvider)
                .getOrders();

        doReturn(3)
                .when(ingestRateProvider)
                .getIngestRate();

        handler.run();
        List<Event> events = eventStorageProvider.getALl();
        Assertions.assertNotNull(events);
        Assertions.assertEquals(3, events.stream().filter(x-> x.getAction() == EventType.INGEST_ORDER).count());
    }
}
