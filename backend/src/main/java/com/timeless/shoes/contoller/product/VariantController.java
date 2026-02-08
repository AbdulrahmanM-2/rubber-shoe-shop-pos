package com.timeless.shoes.controller.product;

import com.timeless.shoes.dto.ApiResponse;
import com.timeless.shoes.product.ProductVariant;
import com.timeless.shoes.product.ProductVariantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/variants")
public class VariantController {

    private final ProductVariantService variantService;

    public VariantController(ProductVariantService variantService) {
        this.variantService = variantService;
    }

    /**
     * Get all variants
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductVariant>>> getAllVariants() {
        List<ProductVariant> variants = variantService.getAllVariants();
        return ResponseEntity.ok(new ApiResponse<>(true, "All variants retrieved", variants));
    }

    /**
     * Get variants for a specific product
     */
    @GetMapping("/product/{productId}")
    public ResponseEntity<ApiResponse<List<ProductVariant>>> getVariantsByProduct(@PathVariable Long productId) {
        List<ProductVariant> variants = variantService.getVariantsByProduct(productId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Variants for product retrieved", variants));
    }

    /**
     * Create a new product variant
     */
    @PostMapping
    public ResponseEntity<ApiResponse<ProductVariant>> createVariant(@RequestBody ProductVariant variant) {
        ProductVariant saved = variantService.createVariant(variant);
        return ResponseEntity.ok(new ApiResponse<>(true, "Variant created successfully", saved));
    }

    /**
     * Update an existing product variant
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductVariant>> updateVariant(
            @PathVariable Long id,
            @RequestBody ProductVariant variant) {
        ProductVariant updated = variantService.updateVariant(id, variant);
        return ResponseEntity.ok(new ApiResponse<>(true, "Variant updated successfully", updated));
    }

    /**
     * Delete a variant (soft delete)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteVariant(@PathVariable Long id) {
        variantService.deleteVariant(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Variant deleted successfully", null));
    }
          }
