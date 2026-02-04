package com.timeless.shoes.sales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("""
        SELECT COALESCE(SUM(s.total), 0)
        FROM Sale s
        WHERE DATE(s.createdAt) = CURRENT_DATE
    """)
    BigDecimal todayTotalSales();
}
