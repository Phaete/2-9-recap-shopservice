package com.phaete;

import java.math.BigDecimal;

public record Product(
        int id,
        String name,
        BigDecimal price,
        int stock
) {
    public boolean hasEnoughStock(int quantity) {
        return stock >= quantity;
    }
}
