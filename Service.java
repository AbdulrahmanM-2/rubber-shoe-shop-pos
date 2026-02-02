package com.shop.report;

import com.shop.sales.SaleReportRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ReportService {

  private final SaleReportRepository repo;

  public ReportService(SaleReportRepository repo) {
    this.repo = repo;
  }

  public DailySalesReport todaySales() {
    Object[] row = (Object[]) repo.getTodaySummary();
    return new DailySalesReport(
        (BigDecimal) row[0],
        (Long) row[1]
    );
  }
}
