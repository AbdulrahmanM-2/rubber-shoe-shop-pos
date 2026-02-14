package com.timeless.shoes.model;

import java.math.BigDecimal;

public class ProductVariant {
    private Long id;
    private String size;
    private String color;
    private BigDecimal sellingPrice;
    private Product product;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public BigDecimal getSellingPrice() { return sellingPrice; }
    public void setSellingPrice(BigDecimal sellingPrice) { this.sellingPrice = sellingPrice; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
}
