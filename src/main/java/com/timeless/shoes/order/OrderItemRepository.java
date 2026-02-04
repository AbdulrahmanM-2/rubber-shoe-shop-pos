package com.timeless.shoes.order;

import com.timeless.shoes.product.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

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
     * List all items of a specific order
     */
    @Query("SELECT oi FROM OrderItem oi WHERE oi.order.id = :orderId")
    java.util.List<OrderItem> findByOrderId(Long orderId);
}
