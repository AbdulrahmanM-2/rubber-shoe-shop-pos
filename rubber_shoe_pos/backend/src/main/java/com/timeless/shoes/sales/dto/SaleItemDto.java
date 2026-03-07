package com.timeless.shoes.sales.dto;
import java.math.BigDecimal;
public class SaleItemDto {
    private Long variantId;
    private String productName;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal total;
    private String size;
    private String color;
    public Long getVariantId() { return variantId; }
    public void setVariantId(Long v) { this.variantId = v; }
    public String getProductName() { return productName; }
    public void setProductName(String p) { this.productName = p; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int q) { this.quantity = q; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal u) { this.unitPrice = u; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal t) { this.total = t; }
    public String getSize() { return size; }
    public void setSize(String s) { this.size = s; }
    public String getColor() { return color; }
    public void setColor(String c) { this.color = c; }
}
