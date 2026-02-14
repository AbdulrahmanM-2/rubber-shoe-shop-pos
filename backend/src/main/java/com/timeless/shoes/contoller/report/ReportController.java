package com.timeless.shoes.controller.report;

import com.timeless.shoes.report.ReportService;
import com.timeless.shoes.report.DailySalesReport;
import com.timeless.shoes.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/sales/today")
    public ApiResponse<List<DailySalesReport>> todaySales() {
        List<DailySalesReport> sales = reportService.todaySales();
        return new ApiResponse<>(true, "Today's sales retrieved", sales);
    }

    @GetMapping("/profit/today")
    public ApiResponse<BigDecimal> todayProfit() {
        BigDecimal profit = reportService.todayProfit();
        return new ApiResponse<>(true, "Today's profit retrieved", profit);
    }

    @GetMapping("/payments/summary")
    public ApiResponse<List<Object[]>> paymentSummary() {
        List<Object[]> summary = reportService.paymentSummary();
        return new ApiResponse<>(true, "Payment summary retrieved", summary);
    }
}
