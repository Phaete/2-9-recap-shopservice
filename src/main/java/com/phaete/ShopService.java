package com.phaete;

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
    public void productOfOrderExists(Order order) {
        for (Product product : order.products().keySet()) {
            if (productRepo.getProduct(product.id()) == null) {
                System.out.println("Product does not exist: " + product.id());
            }
        }
    }

    public void placeOrder(Order order) {
        orderRepo.addOrder(order);
        for (Product product : order.products().keySet()) {
            System.out.println(product);
            System.out.println(order.products().get(product));
            productRepo.decreaseStock(product, order.products().get(product));
        }
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
