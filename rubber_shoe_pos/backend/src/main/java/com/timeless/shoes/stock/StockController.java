package com.timeless.shoes.stock;

import com.timeless.shoes.product.ProductVariant;
import com.timeless.shoes.product.ProductVariantRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/stock")
public class StockController {
    private final StockService stockService;
    private final ProductVariantRepository variantRepository;

    public StockController(StockService stockService, ProductVariantRepository variantRepository) {
        this.stockService = stockService;
        this.variantRepository = variantRepository;
    }

    @GetMapping("/low")
    public ResponseEntity<List<ProductVariant>> getLowStock() {
        return ResponseEntity.ok(variantRepository.findByQuantityLessThanEqual(5));
    }

    @PostMapping("/add/{variantId}/{qty}")
    public ResponseEntity<Void> addStock(@PathVariable Long variantId, @PathVariable int qty) {
        ProductVariant v = variantRepository.findById(variantId)
                .orElseThrow(() -> new RuntimeException("Variant not found"));
        stockService.addStock(v, qty, "MANUAL");
        return ResponseEntity.ok().build();
    }

    @PostMapping("/adjust/{variantId}/{qty}")
    public ResponseEntity<Void> adjustStock(@PathVariable Long variantId, @PathVariable int qty) {
        ProductVariant v = variantRepository.findById(variantId)
                .orElseThrow(() -> new RuntimeException("Variant not found"));
        stockService.adjustStock(v, qty, "ADJUST");
        return ResponseEntity.ok().build();
    }
}
