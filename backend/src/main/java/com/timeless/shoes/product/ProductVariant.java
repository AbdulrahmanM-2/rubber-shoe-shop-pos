package com.timeless.shoes.product;

import jakarta.persistence.*;

@Entity
@Table(name = "product_variants")
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sku;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductVariant() {}

    public Long getId() { return id; }
    public String getSku() { return sku; }
    public Product getProduct() { return product; }

    public void setId(Long id) { this.id = id; }
    public void setSku(String sku) { this.sku = sku; }
    public void setProduct(Product product) { this.product = product; }
}
