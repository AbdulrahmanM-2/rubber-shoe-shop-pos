package com.timeless.shoes.controller.report;

import com.timeless.shoes.dto.ApiResponse;
import com.timeless.shoes.product.ProductVariant;
import com.timeless.shoes.report.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/today-sales")
    public ResponseEntity<ApiResponse<BigDecimal>> todaySales() {
        BigDecimal sales = reportService.todaySales();
        return ResponseEntity.ok(new ApiResponse<>(true, "Today's sales retrieved", sales));
    }

    @GetMapping("/profit")
    public ResponseEntity<ApiResponse<BigDecimal>> todayProfit() {
        BigDecimal profit = reportService.todayProfit();
        return ResponseEntity.ok(new ApiResponse<>(true, "Today's profit retrieved", profit));
    }

    @GetMapping("/payments")
    public ResponseEntity<ApiResponse<List<Object[]>>> paymentSummary() {
        List<Object[]> summary = reportService.paymentSummary();
        return ResponseEntity.ok(new ApiResponse<>(true, "Payment summary retrieved", summary));
    }

    @GetMapping("/low-stock")
    public ResponseEntity<ApiResponse<List<ProductVariant>>> lowStock() {
        List<ProductVariant> lowStock = reportService.lowStock();
        return ResponseEntity.ok(new ApiResponse<>(true, "Low-stock variants retrieved", lowStock));
    }
}
