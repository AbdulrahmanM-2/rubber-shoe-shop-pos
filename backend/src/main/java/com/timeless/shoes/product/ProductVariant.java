package com.timeless.shoes.product;

import java.math.BigDecimal;

public class ProductVariant {
    private Product product;
    private String color;
    private String size;
    private BigDecimal sellingPrice;
    private int quantity;

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public BigDecimal getSellingPrice() { return sellingPrice; }
    public void setSellingPrice(BigDecimal sellingPrice) { this.sellingPrice = sellingPrice; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
