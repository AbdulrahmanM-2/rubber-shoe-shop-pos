package com.timeless.shoes.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cashier_shifts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CashierShift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The cashier who owns this shift
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
    private Double systemTotal; // total recorded sales for this shift

    // Orders/sales associated with this shift
    @OneToMany(mappedBy = "shift", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;

    /**
     * Compute cash variance for the shift
     */
    public Double getCashVariance() {
        if (closingBalance == null || systemTotal == null) return null;
        return closingBalance - systemTotal;
    }

    /**
     * Check if shift is still active
     */
    public boolean isActive() {
        return closedAt == null;
    }
}
