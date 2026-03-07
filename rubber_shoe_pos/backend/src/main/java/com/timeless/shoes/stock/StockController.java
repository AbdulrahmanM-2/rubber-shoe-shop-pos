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
    @GetMapping("/low-stock")
    public ResponseEntity<List<ProductVariant>> getLowStock() {
        return ResponseEntity.ok(variantRepository.findLowStock());
    }
    @PostMapping("/{variantId}/add")
    public ResponseEntity<Void> addStock(@PathVariable Long variantId,
                                         @RequestParam int quantity,
                                         @RequestParam(required = false) String reference) {
        ProductVariant variant = variantRepository.findById(variantId)
                .orElseThrow(() -> new RuntimeException("Variant not found"));
        stockService.addStock(variant, quantity, reference != null ? reference : "MANUAL");
        return ResponseEntity.ok().build();
    }
    @PostMapping("/{variantId}/adjust")
    public ResponseEntity<Void> adjustStock(@PathVariable Long variantId,
                                             @RequestParam int quantity,
                                             @RequestParam(required = false) String reference) {
        ProductVariant variant = variantRepository.findById(variantId)
                .orElseThrow(() -> new RuntimeException("Variant not found"));
        stockService.adjustStock(variant, quantity, reference != null ? reference : "ADJUST");
        return ResponseEntity.ok().build();
    }
}
