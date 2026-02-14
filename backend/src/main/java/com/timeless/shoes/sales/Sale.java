package com.timeless.shoes.sales;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;

    private String paymentType;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<SaleItem> items;

    public Sale() {}

    public Long getId() { return id; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public String getPaymentType() { return paymentType; }
    public List<SaleItem> getItems() { return items; }

    public void setId(Long id) { this.id = id; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setPaymentType(String paymentType) { this.paymentType = paymentType; }
    public void setItems(List<SaleItem> items) { this.items = items; }
}
