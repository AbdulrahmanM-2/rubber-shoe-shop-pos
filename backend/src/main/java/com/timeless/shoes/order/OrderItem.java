package com.timeless.shoes.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private Long id;
    private String productName;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal total;
}
