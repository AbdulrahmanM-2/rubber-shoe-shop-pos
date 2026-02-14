package com.timeless.shoes.sales.dto;

import java.math.BigDecimal;

public class SaleItemDto {

    private Long variantId;
    private int quantity;
    private BigDecimal unitPrice;

    public SaleItemDto() {}

    public Long getVariantId() { return variantId; }
    public int getQuantity() { return quantity; }
    public BigDecimal getUnitPrice() { return unitPrice; }

    public void setVariantId(Long variantId) { this.variantId = variantId; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
}
