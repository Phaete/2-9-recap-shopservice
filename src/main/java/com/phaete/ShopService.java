package com.phaete;

public class ShopService {

    // Variables
    OrderRepo orderRepo;
    ProductRepo productRepo = new ProductRepo();

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

    public void productInEnoughStock(Order order) {
        for (Product product : order.products().keySet()) {
            if (order.products().get(product) > product.stock()) {
                System.out.println("Not enough stock available for the product: " + product.id());
                break;
            }
        }
    }

    public void placeOrder(Order order) {
        orderRepo.addOrder(order);
    }
}
