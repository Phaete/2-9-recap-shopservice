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
        products.put(product, quantity);
    }

    public void removeProduct(Product product) {
        for (Product productKey : products.keySet()) {
            if (productKey.id() == product.id()) {
                products.remove(productKey);
                break;
            }
        }
        System.out.println("Could not remove the specified product, as it is not part of the order.");
    }
}
