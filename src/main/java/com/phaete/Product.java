package com.phaete;

import java.math.BigDecimal;

public record Product(
        int id,
        String name,
        BigDecimal price
) {

}
