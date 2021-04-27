package com.kitchen.dataproviders;

import com.kitchen.dataproviders.dto.Order;

import java.util.Queue;

public interface OrdersProvider {

    Queue<Order> getOrders();
}
