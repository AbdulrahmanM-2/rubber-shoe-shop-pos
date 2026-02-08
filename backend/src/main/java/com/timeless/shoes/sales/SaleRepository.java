package com.timeless.shoes.sale;

import org.springframework.data.jpa.repository.*;
import java.math.BigDecimal;
import java.util.*;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("""
        SELECT COALESCE(SUM(s.total), 0)
        FROM Sale s
        WHERE DATE(s.createdAt) = CURRENT_DATE
    """)
    BigDecimal todaySales();

    @Query("""
        SELECT s
        FROM Sale s
        ORDER BY s.createdAt DESC
    """)
    List<Sale> recentOrders(Pageable pageable);
}
