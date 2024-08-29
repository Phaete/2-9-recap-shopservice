package com.phaete;

import java.time.LocalDate;
import java.util.Map;

public record Order(
        int id,
        Map<Product, Integer> products, //
        LocalDate orderDate
) {
    // Overload constructor for
    public Order(int id, Map<Product, Integer> products) {
        this(id, products, LocalDate.EPOCH);
    }

}
