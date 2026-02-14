package com.timeless.shoes.controller.stock;

import com.timeless.shoes.dto.ApiResponse;
import com.timeless.shoes.dto.UpdateStockRequest;
import com.timeless.shoes.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    /** Get all low-stock items */
    @GetMapping("/low-stock")
    public ApiResponse getLowStockItems() {
        return new ApiResponse(true, stockService.getLowStockItems());
    }

    /** Update stock quantity for a product variant */
    @PostMapping("/update")
    public ApiResponse updateStock(@RequestBody UpdateStockRequest request) {
        stockService.updateStock(request);
        return new ApiResponse(true, "Stock updated successfully");
    }

    /** Optional: get stock summary */
    @GetMapping("/summary")
    public ApiResponse getStockSummary() {
        return new ApiResponse(true, stockService.getStockSummary());
    }
}
