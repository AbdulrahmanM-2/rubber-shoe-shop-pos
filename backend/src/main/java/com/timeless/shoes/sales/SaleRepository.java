package com.timeless.shoes.repository;

import com.timeless.shoes.report.CashMobileMoneyReport;
import com.timeless.shoes.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("""
        SELECT new com.timeless.shoes.report.CashMobileMoneyReport(
            COALESCE(SUM(CASE WHEN s.paymentType = 'CASH' THEN s.total ELSE 0 END), 0),
            COALESCE(SUM(CASE WHEN s.paymentType = 'MOBILE' THEN s.total ELSE 0 END), 0),
            COALESCE(SUM(CASE WHEN s.paymentType = 'CREDIT' THEN s.total ELSE 0 END), 0)
        )
        FROM Sale s
        WHERE s.createdAt >= CURRENT_DATE AND s.createdAt < CURRENT_DATE + 1
    """)
    CashMobileMoneyReport paymentSummary();

    @Query("""
        SELECT s FROM Sale s ORDER BY s.createdAt DESC
    """)
    List<Sale> recentOrders(org.springframework.data.domain.Pageable pageable);
}
