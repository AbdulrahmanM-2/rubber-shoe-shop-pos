package com.timeless.shoes.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Calculate total sales for today
     */
    @Query("""
        SELECT COALESCE(SUM(o.total), 0)
        FROM Order o
        WHERE DATE(o.createdAt) = CURRENT_DATE
    """)
    BigDecimal todayTotalSales();

    /**
     * Calculate total profit for today
     */
    @Query("""
        SELECT COALESCE(SUM((oi.unitPrice - v.costPrice) * oi.quantity), 0)
        FROM OrderItem oi
        JOIN oi.variant v
        WHERE DATE(oi.order.createdAt) = CURRENT_DATE
    """)
    BigDecimal todayProfit();

    /**
     * Payment summary grouped by type (CASH, MOBILE, CREDIT)
     */
    @Query("""
        SELECT o.paymentType, SUM(o.total)
        FROM Order o
        WHERE DATE(o.createdAt) = CURRENT_DATE
        GROUP BY o.paymentType
    """)
    List<Object[]> paymentSummary();
}
