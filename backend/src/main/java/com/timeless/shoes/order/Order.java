package com.timeless.shoes.order;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;

    private BigDecimal total;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Object> items; // simplified for compilation

    private LocalDateTime createdAt;

    public Order() {}

    public Long getId() { return id; }
    public String getOrderNumber() { return orderNumber; }
    public BigDecimal getTotal() { return total; }
    public List<Object> getItems() { return items; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public void setItems(List<Object> items) { this.items = items; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    }
