package com.phaete;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        // Products
        Product apple = new Product(1, "Apple", new BigDecimal("0.09"));
        Product banana = new Product(2, "Banana", new BigDecimal("0.29"));
        Product orange = new Product(3, "Orange", new BigDecimal("0.49"));
        Product peach = new Product(4, "Peach", new BigDecimal("0.69"));

        // List Repos
        OrderRepo orderListRepo = new OrderListRepo();
        ShopService shopServiceList = new ShopService(orderListRepo);

        // Add products to the shopServiceList
        shopServiceList.getProductRepo().addProduct(apple, 100);
        shopServiceList.getProductRepo().addProduct(banana, 100);
        shopServiceList.getProductRepo().addProduct(orange, 50);
        shopServiceList.getProductRepo().addProduct(peach, 50);

        // Map Repos
        OrderRepo orderMapRepo = new OrderMapRepo();
        ShopService shopServiceMap = new ShopService(orderMapRepo);

        // Add products to the shopServiceMap
        shopServiceMap.getProductRepo().addProduct(apple, 100);
        shopServiceMap.getProductRepo().addProduct(banana, 100);
        shopServiceMap.getProductRepo().addProduct(orange, 50);
        shopServiceMap.getProductRepo().addProduct(peach, 50);

        // Create orders
        Order orderApples = new Order(1, "Peter Parker");
        orderApples.addProduct(apple, 2);
        System.out.println(orderApples);

        Order applesAndOranges = new Order(2, "Tony Stark");
        applesAndOranges.addProduct(apple, 3);
        applesAndOranges.addProduct(orange, 5);

        Order fruits = new Order(3, "Bruce Wayne");
        fruits.addProduct(apple, 5);
        fruits.addProduct(banana, 5);
        fruits.addProduct(orange, 5);
        fruits.addProduct(peach, 5);

        // Place orders
        shopServiceList.placeOrder(orderApples);
        shopServiceList.placeOrder(applesAndOranges);
        shopServiceList.placeOrder(fruits);

        shopServiceMap.placeOrder(orderApples);
        shopServiceMap.placeOrder(applesAndOranges);
        shopServiceMap.placeOrder(fruits);

        System.out.println(orderMapRepo.getOrder(applesAndOranges.id()));
        System.out.println("_______________________________________________");
        // Tony wants to modify his order
        Map<Product, Integer> modifications = new HashMap<>();
        modifications.put(apple, 5);
        modifications.put(orange, 3);
        boolean success = shopServiceMap.modifyOrder(applesAndOranges, modifications);
        if (success) {
            System.out.println("Successfully modified the order.");
        } else {
            System.out.println("Could not modify the order, reverting back to previous state.");
        }

        System.out.println(orderMapRepo.getOrder(applesAndOranges.id()));

    }
}