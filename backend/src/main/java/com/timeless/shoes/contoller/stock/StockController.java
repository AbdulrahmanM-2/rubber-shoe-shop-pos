package com.timeless.shoes.controller.stock;

import com.timeless.shoes.dto.ApiResponse;
import com.timeless.shoes.product.ProductVariant;
import com.timeless.shoes.stock.StockMovement;
import com.timeless.shoes.stock.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    /**
     * Get all product variants with current stock
     */
    @GetMapping("/variants")
    public ResponseEntity<ApiResponse<List<ProductVariant>>> allVariants() {
        List<ProductVariant> variants = stockService.getAllVariants();
        return ResponseEntity.ok(new ApiResponse<>(true, "All variants retrieved", variants));
    }

    /**
     * Add stock (IN)
     */
    @PostMapping("/in")
    public ResponseEntity<ApiResponse<StockMovement>> addStock(@RequestBody StockMovement movement) {
        StockMovement saved = stockService.addStock(movement);
        return ResponseEntity.ok(new ApiResponse<>(true, "Stock added", saved));
    }

    /**
     * Reduce stock (OUT)
     */
    @PostMapping("/out")
    public ResponseEntity<ApiResponse<StockMovement>> reduceStock(@RequestBody StockMovement movement) {
        StockMovement saved = stockService.reduceStock(movement);
        return ResponseEntity.ok(new ApiResponse<>(true, "Stock reduced", saved));
    }

    /**
     * Adjust stock manually (ADJUST)
     */
    @PostMapping("/adjust")
    public ResponseEntity<ApiResponse<StockMovement>> adjustStock(@RequestBody StockMovement movement) {
        StockMovement saved = stockService.adjustStock(movement);
        return ResponseEntity.ok(new ApiResponse<>(true, "Stock adjusted", saved));
    }

    /**
     * Get stock movements for a specific variant
     */
    @GetMapping("/movements/{variantId}")
    public ResponseEntity<ApiResponse<List<StockMovement>>> variantMovements(@PathVariable Long variantId) {
        List<StockMovement> movements = stockService.getMovementsByVariant(variantId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Stock movements retrieved", movements));
    }
}
