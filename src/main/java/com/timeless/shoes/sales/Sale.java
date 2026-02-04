package com.timeless.shoes.sales;

import com.timeless.shoes.customers.Customer;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String saleNo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @Column(nullable = false)
    private BigDecimal total = BigDecimal.ZERO;

    private String paymentType; // CASH, MOBILE, CREDIT

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<SaleItem> items;

    // Getters & setters
}
