
package com.timeless.shoes.report;

import com.timeless.shoes.product.ProductVariant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;
    private final SaleReportRepository saleReportRepository;
    private final VariantRepository variantRepository;

    public ReportController(
            ReportService reportService,
            SaleReportRepository saleReportRepository,
            VariantRepository variantRepository
    ) {
        this.reportService = reportService;
        this.saleReportRepository = saleReportRepository;
        this.variantRepository = variantRepository;
    }

    /**
     * Today sales summary
     */
    @GetMapping("/today")
    public DailySalesReport todaySales() {
        return reportService.todaySales();
    }

    /**
     * Today profit (selling - cost)
     */
    @GetMapping("/profit")
    public BigDecimal todayProfit() {
        return saleReportRepository.todayProfit();
    }

    /**
     * Payment summary (CASH / MOBILE / CREDIT)
     */
    @GetMapping("/payments")
    public List<Object[]> paymentSummary() {
        return saleReportRepository.paymentSummary();
    }

    /**
     * Low stock alert (variant-level stock)
     */
    @GetMapping("/low-stock")
    public List<ProductVariant> lowStock() {
        return variantRepository.lowStock();
    }
      }
