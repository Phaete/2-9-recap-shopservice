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
     * @return true if all the products in the order exist, false if not.
     */
    public boolean productOfOrderExists(Order order) {
        for (Product product : order.products().keySet()) {
            if (productRepo.getProduct(product.id()) == null) {
                System.out.println("Product does not exist: " + product.id());
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if all the products in the order have enough stock in the product repository.
     * Prints a message to the console for each product that does not have enough stock.
     *
     * @param order The order to be checked.
     * @return true if all the products in the order have enough stock, false if not.
     */
    public boolean productStockOfOrderExists(Order order) {
        for (Product product : order.products().keySet()) {
            if (!productRepo.hasEnoughStock(product, order.products().get(product))) {
                System.out.println("Product is not in stock: " + product.id());
                return false;
            }
        }
        return true;
    }

    /**
     * Places an order. This involves adding the order to the order repository, and subtracting the quantities of the
     * products from the product repository.
     *
     * @param order The order to be placed
     */
    public void placeOrder(Order order) {
        if (productOfOrderExists(order) && productStockOfOrderExists(order)) {
            orderRepo.addOrder(order);
            for (Product product : order.products().keySet()) {
                productRepo.decreaseStock(
                        productRepo.getProduct(product.id()),
                        order.products().get(product)
                );
            }
        }
    }

    /**
     * Removes an order. This involves removing the order from the order repository, and increasing the quantities of
     * the products in the product repository.
     *
     * @param order The order to be removed
     */
    public void cancelOrder(Order order) {
        if (productOfOrderExists(order) && productStockOfOrderExists(order)) {
            orderRepo.removeOrder(order);
            for (Product product : order.products().keySet()) {
                productRepo.increaseStock(
                        productRepo.getProduct(product.id()),
                        order.products().get(product)
                );
            }
        }
    }

    public boolean modifyOrder(Order order, Map<Product, Integer> modifications) {
        // Check if all products in the order can be changed before actually changing anything
        for (Product product : modifications.keySet()) {
            if (!productRepo.hasEnoughStock(product, modifications.get(product))) {
                System.out.println("Product is not in high enough stock available to apply the modification: " + product.id() + ". Left over stock: " + productRepo.getProductRepo().get(product));
                return false;
            }
        }
        // Cancel the old order
        cancelOrder(order);
        // Create a new order and change it based on the modifications
        Order modifiedOrder = new Order(order.id(), order.products(), order.customerName(), order.orderDate());
        for (Product product : modifiedOrder.products().keySet()) {
            modifiedOrder.addProduct(product, modifications.get(product));
        }
        // Place the new order, which is the old order with the modifications applied
        placeOrder(modifiedOrder);
        return true;
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
