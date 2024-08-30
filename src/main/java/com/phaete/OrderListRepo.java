package com.phaete;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    /**
     * Modifies an existing order.
     *
     * @param order The order to be modified.
     * @param modifications The modifications to be made to the order.
     * @return true if all the products in the order can be modified, false if not.
     */
    public boolean modifyOrder(Order order, Map<Product, Integer> modifications) {
        boolean success = true;
        List<Order> orderListCopy = new ArrayList<>(orderList);
        // Check if all products in the order can be changed before actually changing anything
        for (Product product : orderListCopy.get(orderListCopy.indexOf(getOrder(order.id()))).products().keySet()) {
            // check if the product id of the product in the order matches the product id of the product in the modifications
            for (Product modification : modifications.keySet()) {
                if(product.id() == modification.id()) {
                    // In the order itself, change the quantity as per the modification
                    orderListCopy.get(orderListCopy.indexOf(getOrder(order.id()))).addProduct(modification, modifications.get(modification));
                    orderListCopy.set(orderListCopy.indexOf(getOrder(order.id())), new Order(order.id(), modifications, order.customerName(), order.orderDate()));
                } else {
                    success = false;
                }
            }
        }
        if (success) {
            orderList = orderListCopy;
        }
        return success;
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
