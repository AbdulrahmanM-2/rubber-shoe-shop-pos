package com.timeless.shoes.sales;

import com.timeless.shoes.product.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {

    @Query("""
        SELECT COALESCE(SUM((si.unitPrice - v.costPrice) * si.quantity), 0)
        FROM SaleItem si
        JOIN si.variant v
        WHERE DATE(si.sale.createdAt) = CURRENT_DATE
    """)
    BigDecimal todayProfit();
}
