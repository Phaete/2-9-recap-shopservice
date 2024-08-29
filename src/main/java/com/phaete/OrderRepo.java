package com.phaete;

import java.math.BigDecimal;
import java.util.List;

public interface OrderRepo {

    public void addOrder(Order order);
    public void removeOrder(Order order);
    public Order getOrder(int orderId);
    public List<Order> getOrders();
    public BigDecimal getTotalPrice(int orderId);


}
