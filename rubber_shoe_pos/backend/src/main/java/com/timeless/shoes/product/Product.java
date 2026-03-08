package com.timeless.shoes.product;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="products") @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Product {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private String name;
    private String brand;
    private String gender;
    private String category;
    private boolean active = true;
}
