package com.timeless.shoes.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cashier_shifts")
public class CashierShift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cashier_id", nullable = false)
    private User cashier;

    @Column(name = "opened_at", nullable = false)
    private LocalDateTime openedAt;

    @Column(name = "closed_at")
    private LocalDateTime closedAt;

    @Column(name = "opening_balance", nullable = false)
    private Double openingBalance;

    @Column(name = "closing_balance")
    private Double closingBalance;

    @Column(name = "system_total")
    private Double systemTotal;

    @OneToMany(mappedBy = "shift", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;

    public CashierShift() {}

    // Builder (replaces Lombok @Builder)
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final CashierShift obj = new CashierShift();
        public Builder cashier(User v)            { obj.cashier = v; return this; }
        public Builder openedAt(LocalDateTime v)  { obj.openedAt = v; return this; }
        public Builder closedAt(LocalDateTime v)  { obj.closedAt = v; return this; }
        public Builder openingBalance(Double v)   { obj.openingBalance = v; return this; }
        public Builder closingBalance(Double v)   { obj.closingBalance = v; return this; }
        public Builder systemTotal(Double v)      { obj.systemTotal = v; return this; }
        public CashierShift build()               { return obj; }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getCashier() { return cashier; }
    public void setCashier(User cashier) { this.cashier = cashier; }
    public LocalDateTime getOpenedAt() { return openedAt; }
    public void setOpenedAt(LocalDateTime openedAt) { this.openedAt = openedAt; }
    public LocalDateTime getClosedAt() { return closedAt; }
    public void setClosedAt(LocalDateTime closedAt) { this.closedAt = closedAt; }
    public Double getOpeningBalance() { return openingBalance; }
    public void setOpeningBalance(Double openingBalance) { this.openingBalance = openingBalance; }
    public Double getClosingBalance() { return closingBalance; }
    public void setClosingBalance(Double closingBalance) { this.closingBalance = closingBalance; }
    public Double getSystemTotal() { return systemTotal; }
    public void setSystemTotal(Double systemTotal) { this.systemTotal = systemTotal; }
    public List<Order> getOrders() { return orders; }
    public void setOrders(List<Order> orders) { this.orders = orders; }
    public boolean isActive() { return closedAt == null; }
    public Double getCashVariance() {
        if (closingBalance == null || systemTotal == null) return null;
        return closingBalance - systemTotal;
    }
}
