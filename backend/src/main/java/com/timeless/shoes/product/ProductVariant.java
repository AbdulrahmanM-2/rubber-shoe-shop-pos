package com.timeless.shoes.product;

import java.math.BigDecimal;

public class ProductVariant {
    private Long id;
    private String name;
    private int quantity;
    private BigDecimal sellingPrice;
    private String size;
    private String color;
    private Product product; // reference to Product

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public BigDecimal getSellingPrice() { return sellingPrice; }
    public void setSellingPrice(BigDecimal sellingPrice) { this.sellingPrice = sellingPrice; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
}
