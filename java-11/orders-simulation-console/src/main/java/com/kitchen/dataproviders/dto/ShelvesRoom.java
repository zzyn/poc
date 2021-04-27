package com.kitchen.dataproviders.dto;

import com.kitchen.core.entity.Shelf;
import com.kitchen.core.entity.Temperature;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString()
public class ShelvesRoom {

    private static Integer overflowShelfCapacity = 15;
    private static Integer coldShelfCapacity = 10;
    private static Integer hotShelfCapacity = 10;
    private static Integer frozenShelfCapacity = 10;
    private static Integer singleTemperatureShelfDecayModifier = 1;
    private static Integer overflowTemperatureShelfDecayModifier = 2;
    private ConcurrentMap<Temperature, Shelf> temperatureShelfMap;
    private ConcurrentMap<Order, Shelf> orderShelvesMap;
    private ConcurrentMap<Shelf, List<Order>> shelfOrdersMap;

    private static ShelvesRoom builder() {
        ShelvesRoom room = new ShelvesRoom();

        Shelf coldShelf = new Shelf()
                .setShelfDecayModifier(singleTemperatureShelfDecayModifier)
                .setCapacity(coldShelfCapacity)
                .setAllowableTemperature(Temperature.COLD)
                .setName(Temperature.COLD.getText());

        Shelf hotShelf = new Shelf()
                .setShelfDecayModifier(singleTemperatureShelfDecayModifier)
                .setCapacity(hotShelfCapacity)
                .setAllowableTemperature(Temperature.HOT)
                .setName(Temperature.HOT.getText());

        Shelf frozenShelf = new Shelf()
                .setShelfDecayModifier(singleTemperatureShelfDecayModifier)
                .setCapacity(frozenShelfCapacity)
                .setAllowableTemperature(Temperature.FROZEN)
                .setName(Temperature.FROZEN.getText());

        Shelf overflowShelf = new Shelf()
                .setShelfDecayModifier(overflowTemperatureShelfDecayModifier)
                .setCapacity(overflowShelfCapacity)
                .setAllowableTemperature(Temperature.ANY)
                .setName("overflow");

        room.setOrderShelvesMap(new ConcurrentHashMap<>());

        ConcurrentMap<Shelf, List<Order>> shelfOrdersMap = new ConcurrentHashMap<>();
        shelfOrdersMap.put(coldShelf, Collections.synchronizedList(new ArrayList<>()));
        shelfOrdersMap.put(frozenShelf, Collections.synchronizedList(new ArrayList<>()));
        shelfOrdersMap.put(hotShelf, Collections.synchronizedList(new ArrayList<>()));
        shelfOrdersMap.put(overflowShelf, Collections.synchronizedList(new ArrayList<>()));
        room.setShelfOrdersMap(shelfOrdersMap);

        ConcurrentMap<Temperature, Shelf> temperatureShelfMap = new ConcurrentHashMap<>();
        temperatureShelfMap.put(Temperature.COLD, coldShelf);
        temperatureShelfMap.put(Temperature.HOT, hotShelf);
        temperatureShelfMap.put(Temperature.FROZEN, frozenShelf);
        temperatureShelfMap.put(Temperature.ANY, overflowShelf);
        room.setTemperatureShelfMap(temperatureShelfMap);

        return room;
    }

    public static ShelvesRoom getInstance() {
        return Holder.instance;
    }

    //todo: compare is not thread safe
    public synchronized ShelvesRoom putOrderIntoShelves(Order order) {

        Shelf overflowShelf = temperatureShelfMap.get(Temperature.ANY);
        int overflowShelfCapacity = overflowShelf.getCapacity();
        Shelf singleTemperatureShelf = temperatureShelfMap.get(Temperature.fromText(order.getTemp()));

        refreshOrdersOfShelvesInRoom();
        if (shelfOrdersMap.get(singleTemperatureShelf).size() < singleTemperatureShelf.getCapacity()) {
            List<Order> orders = shelfOrdersMap.get(singleTemperatureShelf);
            orders.add(order);
            orderShelvesMap.putIfAbsent(order, singleTemperatureShelf);
        }
        else if (shelfOrdersMap.get(overflowShelf).size() < overflowShelfCapacity) {
            List<Order> overflowOrders = shelfOrdersMap.get(overflowShelf);
            overflowOrders.add(order);
            orderShelvesMap.putIfAbsent(order, overflowShelf);
        }
        else if (shelfOrdersMap.get(overflowShelf).size() == overflowShelfCapacity && shelfOrdersMap.get(singleTemperatureShelf).size() == singleTemperatureShelf.getCapacity()) {
            //random remove order from overflow shelf
            List<Order> overflowOrders = shelfOrdersMap.get(overflowShelf);
            int index = ThreadLocalRandom.current().nextInt(0, overflowShelf.getCapacity() - 1);
            Order removeOrder = overflowOrders.remove(index);
            overflowOrders.add(order);
            this.orderShelvesMap.remove(removeOrder);
            this.orderShelvesMap.putIfAbsent(order, overflowShelf);
        }

        return this;
    }

    public synchronized Order pickUpOrderFromShelves(Order order) {
        refreshOrdersOfShelvesInRoom();
        Shelf shelf = orderShelvesMap.get(order);
        if (shelf != null) {
            List<Order> orders = shelfOrdersMap.get(shelf);
            orders.remove(order);
            orderShelvesMap.remove(order);
            return order;
        }
        return null;
    }

    public void clear() {
        orderShelvesMap.clear();
        shelfOrdersMap.get(temperatureShelfMap.get(Temperature.ANY)).clear();
        shelfOrdersMap.get(temperatureShelfMap.get(Temperature.COLD)).clear();
        shelfOrdersMap.get(temperatureShelfMap.get(Temperature.FROZEN)).clear();
        shelfOrdersMap.get(temperatureShelfMap.get(Temperature.HOT)).clear();
    }

    /**
     * refresh orders state of shelves in room
     * 1.calculate the deteriorated value of order and remove the order which deteriorate <= 0 from shelf
     * 2.move the one order from overflow shelf to single temperature shelf when overflow shelf is full
     * DeterioratedValue = (shelfLife - orderAge - decayRate * orderAge * shelfDecayModifier) / shelfLife
     *
     * @return true
     */
    private boolean refreshOrdersOfShelvesInRoom() {

        Order needRemovedOrder = null;
        for (Map.Entry<Order, Shelf> entry : orderShelvesMap.entrySet()) {
            if (needRemovedOrder != null) {
                orderShelvesMap.remove(needRemovedOrder);
                needRemovedOrder = null;
            }
            Order o = entry.getKey();
            Shelf s = entry.getValue();
            int shelfLife = o.getShelfLife();
            double decayRate = o.getDecayRate();
            long orderAge = o.getOrderAge();
            int shelfDecayModifier = s.getShelfDecayModifier();

            Double newShelfLife = Double.valueOf(shelfLife);
            if(shelfLife > 0) {
                newShelfLife = (shelfLife - orderAge - decayRate * orderAge * shelfDecayModifier) / shelfLife;
                o.setShelfLife(newShelfLife.intValue());
            }

            if(newShelfLife <= 0) {
                List<Order> orders = shelfOrdersMap.get(s);
                orders.remove(o);
                needRemovedOrder = o;
            }
        }

        if (needRemovedOrder != null) {
            orderShelvesMap.remove(needRemovedOrder);
            needRemovedOrder = null;
        }

        Shelf overflowShelf = temperatureShelfMap.get(Temperature.ANY);
        int overflowShelfCapacity = overflowShelf.getCapacity();
        if (shelfOrdersMap.get(overflowShelf).size() == overflowShelfCapacity) {
            moveOverflowShelfOrderToSingleTemperatureShelf();
        }

        return true;
    }

    private void moveOverflowShelfOrderToSingleTemperatureShelf() {
        Shelf overflowShelf = temperatureShelfMap.get(Temperature.ANY);
        int overflowShelfCapacity = overflowShelf.getCapacity();
        List<Order> overflowOrders = shelfOrdersMap.get(overflowShelf);

        for (int i = overflowShelfCapacity - 1; i > 0; i--) {
            Order oo = overflowOrders.get(i);
            Shelf singleTemperatureShelf = temperatureShelfMap.get(Temperature.fromText(oo.getTemp()));
            Collection<Order> singleShelfOrders = shelfOrdersMap.get(singleTemperatureShelf);
            if (singleShelfOrders.size() < singleTemperatureShelf.getCapacity()) {
                Order removedOrder = overflowOrders.remove(i);
                singleShelfOrders.add(removedOrder);
                orderShelvesMap.remove(removedOrder);
                orderShelvesMap.putIfAbsent(removedOrder, singleTemperatureShelf);
                i = 0;
            }
        }
    }

    private static class Holder {
        public static ShelvesRoom instance = ShelvesRoom.builder();
    }
}
