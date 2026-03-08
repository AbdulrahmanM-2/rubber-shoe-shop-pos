package com.timeless.shoes.product;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity @Table(name="product_variants") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ProductVariant {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="product_id",nullable=false)
    private Product product;
    private String size;
    private String color;
    @Column(name="cost_price") private BigDecimal costPrice;
    @Column(name="selling_price") private BigDecimal sellingPrice;
    private Integer quantity = 0;
    @Column(name="reorder_level") private Integer reorderLevel = 5;
}
