package com.shop.sales;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;

public interface SaleReportRepository extends CrudRepository<Sale, Long> {

  @Query("""
    SELECT COALESCE(SUM(s.total), 0), COUNT(s)
    FROM Sale s
    WHERE DATE(s.createdAt) = CURRENT_DATE
  """)
  Object getTodaySummary();
}
