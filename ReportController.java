package com.shop.report;

import com.shop.product.ProductVariant;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

  private final ReportService service;
  private final SaleReportRepository saleRepo;
  private final VariantRepository variantRepo;

  public ReportController(
      ReportService service,
      SaleReportRepository saleRepo,
      VariantRepository variantRepo
  ) {
    this.service = service;
    this.saleRepo = saleRepo;
    this.variantRepo = variantRepo;
  }

  @GetMapping("/today")
  public DailySalesReport todaySales() {
    return service.todaySales();
  }

  @GetMapping("/profit")
  public BigDecimal todayProfit() {
    return saleRepo.todayProfit();
  }

  @GetMapping("/payments")
  public List<Object[]> paymentSummary() {
    return saleRepo.paymentSummary();
  }

  @GetMapping("/low-stock")
  public List<ProductVariant> lowStock() {
    return variantRepo.lowStock();
  }
}
