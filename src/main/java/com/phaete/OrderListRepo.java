package com.phaete;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public BigDecimal getTotalPrice(int orderId) {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Order order : orderList) {
            if (order.id() == orderId) {
                for (Product product : order.products().keySet()) {
                    totalPrice = totalPrice.add(
                            product.price().multiply(
                                    BigDecimal.valueOf(order.products().get(product))
                            )
                    );
                }
            }
        }
        return totalPrice;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderListRepo that = (OrderListRepo) o;
        return Objects.equals(orderList, that.orderList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orderList);
    }

    @Override
    public String toString() {
        return "OrderListRepo{" +
                "orderList=" + orderList +
                '}';
    }
}
