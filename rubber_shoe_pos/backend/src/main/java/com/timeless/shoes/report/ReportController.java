package com.timeless.shoes.report;

import com.timeless.shoes.product.ProductVariant;
import com.timeless.shoes.product.ProductVariantRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportService reportService;
    private final ProductVariantRepository variantRepository;

    public ReportController(ReportService reportService,
                            ProductVariantRepository variantRepository) {
        this.reportService = reportService;
        this.variantRepository = variantRepository;
    }

    @GetMapping("/today")
    public DailySalesReport todaySales() {
        return reportService.todaySales();
    }

    @GetMapping("/low-stock")
    public List<ProductVariant> lowStock() {
        return variantRepository.findByQuantityLessThanEqual(5);
    }
}
