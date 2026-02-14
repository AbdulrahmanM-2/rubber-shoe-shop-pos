package com.timeless.shoes.sales.dto;

import java.math.BigDecimal;

public class SaleItemDto {

    private Long variantId;
    private String name;      // product or variant name
    private int quantity;
    private BigDecimal unitPrice;

    public SaleItemDto() {}

    public Long getVariantId() { return variantId; }
    public void setVariantId(Long variantId) { this.variantId = variantId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
}
