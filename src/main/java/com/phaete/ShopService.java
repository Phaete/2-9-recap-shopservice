package com.phaete;

import java.util.Map;
import java.util.Objects;

public class ShopService {

    // Variables
    private OrderRepo orderRepo;
    private ProductRepo productRepo = new ProductRepo();

    // Constructors
    public ShopService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    // Methods

    /**
     * Checks if all the products in the order exist in the product repository.
     * Prints a message to the console if the product does not exist.
     *
     * @param order The order to be checked.
     */
    public void productOfOrderExists(Order order) {
        for (Product product : order.products().keySet()) {
            if (productRepo.getProduct(product.id()) == null) {
                System.out.println("Product does not exist: " + product.id());
            }
        }
    }

    /**
     * Checks if all the products in the order have enough stock in the product repository.
     * Prints a message to the console for each product that does not have enough stock.
     *
     * @param order The order to be checked.
     */
    public void productStockOfOrderExists(Order order) {
        for (Product product : order.products().keySet()) {
            if (!productRepo.getProduct(product.id()).hasEnoughStock(order.products().get(product))) {
                System.out.println("Product is not in stock: " + product.id());
            }
        }
    }

    /**
     * Places an order. This involves adding the order to the order repository, and subtracting the quantities of the
     * products from the product repository.
     *
     * @param order The order to be placed
     */
    public void placeOrder(Order order) {
        orderRepo.addOrder(order);
        for (Product product : order.products().keySet()) {
            productRepo.decreaseStock(
                    productRepo.getProduct(product.id()),
                    order.products().get(product)
            );
        }
    }

    /**
     * Removes an order. This involves removing the order from the order repository, and increasing the quantities of
     * the products in the product repository.
     *
     * @param order The order to be removed
     */
    public void removeOrder(Order order) {
        orderRepo.removeOrder(order);
        for (Product product : order.products().keySet()) {
            productRepo.increaseStock(
                    productRepo.getProduct(product.id()),
                    order.products().get(product)
            );
        }
    }

    public boolean modifyOrder(Order order, Map<Product, Integer> modifications) {
        return orderRepo.modifyOrder(order, modifications);
    }

    public OrderRepo getOrderRepo() {
        return orderRepo;
    }

    public ProductRepo getProductRepo() {
        return productRepo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopService that = (ShopService) o;
        return Objects.equals(orderRepo, that.orderRepo) && Objects.equals(productRepo, that.productRepo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderRepo, productRepo);
    }

    @Override
    public String toString() {
        return "ShopService{" +
                "orderRepo=" + orderRepo +
                ", productRepo=" + productRepo +
                '}';
    }
}
