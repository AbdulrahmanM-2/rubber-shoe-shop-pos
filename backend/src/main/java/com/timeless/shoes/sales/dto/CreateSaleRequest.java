package com.timeless.shoes.sales.dto;

import java.math.BigDecimal;

public class CreateSaleRequest {
    private Long variantId;
    private int quantity;
    private BigDecimal unitPrice;
    private String paymentType; // added to fix getPaymentType() error

    public Long getVariantId() { return variantId; }
    public void setVariantId(Long variantId) { this.variantId = variantId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }

    public String getPaymentType() { return paymentType; }
    public void setPaymentType(String paymentType) { this.paymentType = paymentType; }
}
