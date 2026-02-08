package com.timeless.shoes.dto;

import java.math.BigDecimal;

public class ProductVariantDto {
    private Long id;
    private Long productId;
    private String size;
    private String color;
    private BigDecimal costPrice;
    private BigDecimal sellingPrice;
    private int quantity;
    private int reorderLevel;

    // Getters & setters
}
