package com.kitchen.cases.component;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.kitchen.app.App;
import com.kitchen.core.handler.IngestOrderHandler;
import com.kitchen.dataproviders.CountDownLatchProvider;
import com.kitchen.dataproviders.EventStorageProvider;
import com.kitchen.dataproviders.OrdersProvider;
import com.kitchen.dataproviders.ShelvesRoomProvider;
import com.kitchen.dataproviders.dto.Event;
import com.kitchen.dataproviders.dto.EventType;
import com.kitchen.dataproviders.dto.Order;
import com.kitchen.dataproviders.dto.ShelvesRoom;
import com.kitchen.mock.bind.MockAppModule;
import com.kitchen.mock.data.MockUtils;
import com.kitchen.mock.impl.MockAppExitHandlerCountDownLatchImpl;
import lombok.extern.slf4j.Slf4j;
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
import java.util.concurrent.CountDownLatch;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class AppComponentTest {

    private App app;
    private Injector injector;

    @Mock
    private OrdersProvider ordersProvider;

    private EventStorageProvider eventStorageProvider;

    private ShelvesRoom shelvesRoom;

    private IngestOrderHandler handler;

    @Mock
    private CountDownLatchProvider countDownLatchProvider;

    private CountDownLatch latch;

    @BeforeEach
    public void setUp() {
        latch = new CountDownLatch(1);
        injector = Guice.createInjector(new MockAppModule(ordersProvider, new MockAppExitHandlerCountDownLatchImpl(latch, countDownLatchProvider), countDownLatchProvider));
        handler = injector.getInstance(IngestOrderHandler.class);
        app = injector.getInstance(App.class);
        shelvesRoom = injector.getInstance(ShelvesRoomProvider.class).getShelvesRoom();
        eventStorageProvider = injector.getInstance(EventStorageProvider.class);
        eventStorageProvider.clear();
    }

    @AfterEach
    public void tearDown() {
        shelvesRoom.clear();
        eventStorageProvider.clear();
    }

    @Test
    public void sample_1() throws InterruptedException {

        List<Order> orders = MockUtils.mockListFromFile("data/app/orders.json", Order.class);
        Queue<Order> queue = new ConcurrentLinkedDeque<>();
        queue.addAll(orders);

        doReturn(queue)
                .when(ordersProvider)
                .getOrders();

        doReturn(new CountDownLatch(1))
                .when(countDownLatchProvider)
                .getLatch();

        app.execute();
        latch.await();

        List<Event> events = eventStorageProvider.getALl();
        Assertions.assertNotNull(events);
        Assertions.assertEquals(3, events.stream().filter(x-> x.getAction() == EventType.INGEST_ORDER).count());
        Assertions.assertEquals(1, events.stream().filter(x-> x.getOrder().getId().compareToIgnoreCase(orders.get(0).getId()) == 0  && x.getAction() == EventType.INGEST_ORDER).count());
        Assertions.assertEquals(1, events.stream().filter(x-> x.getOrder().getId().compareToIgnoreCase(orders.get(1).getId()) == 0  && x.getAction() == EventType.INGEST_ORDER).count());
        Assertions.assertEquals(1, events.stream().filter(x-> x.getOrder().getId().compareToIgnoreCase(orders.get(2).getId()) == 0  && x.getAction() == EventType.INGEST_ORDER).count());
    }

}
