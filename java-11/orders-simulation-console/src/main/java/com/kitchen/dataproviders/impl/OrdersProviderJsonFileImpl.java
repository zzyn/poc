package com.kitchen.dataproviders.impl;

import com.kitchen.dataproviders.OrdersProvider;
import com.kitchen.dataproviders.dto.Order;
import com.kitchen.utils.MockUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
public class OrdersProviderJsonFileImpl implements OrdersProvider {

    private static final String ORDER_FILE = "data/orders.json";

    private static Queue<Order> builder() {
        List<Order> orders = MockUtils.mockListFromFile(ORDER_FILE, Order.class);
        Queue<Order> queue = new ConcurrentLinkedQueue<>();
        queue.addAll(orders);
        return queue;
    }

    public static Queue<Order> getInstance() {
        return OrdersProviderJsonFileImpl.Holder.instance;
    }

    private static class Holder {
        public static Queue<Order> instance = OrdersProviderJsonFileImpl.builder();
    }

    @Override
    public Queue<Order> getOrders() {
        return OrdersProviderJsonFileImpl.getInstance();
    }


}

