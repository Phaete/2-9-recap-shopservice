package com.phaete;

import java.util.ArrayList;
import java.util.List;

public class OrderListRepo implements OrderRepo {

    // Variables
    List<Order> orderList = new ArrayList<>();

    // Constructors


    // Methods
    public void addOrder(Order order) {
        orderList.add(order);
    }

    public void removeOrder(Order order) {
        orderList.remove(order);
    }

    public Order getOrder(int orderId) {
        for (Order order : orderList) {
            if (order.id() == orderId) {
                return order;
            }
        }
        return null; // return null if the product id is not in the list
    }

    public List<Order> getOrders() {
        return orderList;
    }
}
