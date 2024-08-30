package com.phaete;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface OrderRepo {

    void addOrder(Order order);
    void removeOrder(Order order);
    Order getOrder(int orderId);
    List<Order> getOrders();
    BigDecimal getTotalPrice(int orderId);
    boolean modifyOrder(Order order, Map<Product, Integer> modifications);
}
