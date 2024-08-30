package com.phaete;

import java.math.BigDecimal;

public record Product(
        int id,
        String name,
        BigDecimal price,
        int stock
) {
    /**
     * Checks if the stock of the product is greater or equal to the specified quantity
     *
     * @param quantity the quantity to check
     * @return true if the stock is greater or equal to the specified quantity
     */
    public boolean hasEnoughStock(int quantity) {
        return stock >= quantity;
    }

    public Product increaseStock(int quantity) {
        return new Product(id, name, price, stock + quantity);
    }

    public Product decreaseStock(int quantity) {
        return new Product(id, name, price, stock - quantity);
    }
}
