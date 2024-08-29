package com.phaete;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderMapRepo implements OrderRepo {

    // Variables
    Map<Integer, Order> orderMap = new HashMap<>();

    // Constructors


    // Methods
    @Override
    public void addOrder(Order order) {
        orderMap.put(order.id(), order);
    }

    @Override
    public void removeOrder(Order order) {
        orderMap.remove(order.id());
    }

    @Override
    public Order getOrder(int orderId) {
        return orderMap.get(orderId);
    }

    @Override
    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        orders.addAll(orderMap.values());
        return orders;
    }
}
