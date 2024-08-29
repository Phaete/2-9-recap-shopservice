package com.phaete;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public record Order(
        int id,
        Map<Product, Integer> products, // product, quantity
        String customerName,
        LocalDate orderDate
) {
    // Overload constructor to default to todays date
    public Order(int id, Map<Product, Integer> products, String customerName) {
        this(id, products, customerName, LocalDate.EPOCH);
    }

    public Order(int id, String customerName) {
        this(id, new HashMap<>(), customerName, LocalDate.EPOCH);
    }

    public void addProduct(Product product, int quantity) {
        if (product.hasEnoughStock(quantity)) {
            products.put(product, quantity);
        } else {
            System.out.println("Can't add the specified quantity of the product: " + product.id() + " to the order as there's not enough in stock.");
        }
    }

    public void modifyOrder(Map<Product, Integer> modifications) {
        // for each suggested modification, check if the product exists in the order and change the value as per the suggested modification
        for (Product product : products.keySet()) {
            if (modifications.containsKey(product)) {
                products.put(product, modifications.get(product)); // overwrite the old value of the product with the new value
            } else {
                System.out.println("The order does not contain the Product with the id: " + product.id());
            }
        }
    }
}
