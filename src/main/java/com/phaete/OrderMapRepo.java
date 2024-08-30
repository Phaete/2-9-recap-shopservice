package com.phaete;

import java.math.BigDecimal;
import java.util.*;

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

    @Override
    public BigDecimal getTotalPrice(int orderId) {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Order order : orderMap.values()) {
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

    @Override
    public boolean modifyOrder(Order order, Map<Product, Integer> modifications) {
        boolean success = true;
        Map<Integer, Order> orderMapCopy = new HashMap<>(orderMap);
        // Check if all products in the order can be changed before actually changing anything
        for (Product product : orderMap.get(order.id()).products().keySet()) {
            // check if the product id of the product in the order matches the product id of the product in the modifications
            for (Product modifiedProduct : modifications.keySet()) {
                if(product.id() == modifiedProduct.id()) {
                    orderMapCopy.get(order.id()).removeProduct(product);
                    orderMapCopy.get(order.id()).addProduct(modifiedProduct, modifications.get(modifiedProduct));
                    break;
                } else {
                    success = false;
                }
            }
        }
        if (success) {
            orderMap = orderMapCopy;
        }
        return success;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderMapRepo that = (OrderMapRepo) o;
        return Objects.equals(orderMap, that.orderMap);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orderMap);
    }

    @Override
    public String toString() {
        return "OrderMapRepo{" +
                "orderMap=" + orderMap +
                '}';
    }
}
