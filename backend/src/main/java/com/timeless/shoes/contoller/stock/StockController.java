package com.timeless.shoes.controller.customer;

import com.timeless.shoes.dto.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @GetMapping("/{id}")
    public ApiResponse<Object> getCustomer(@PathVariable Long id) {
        return new ApiResponse<>(true, "Customer placeholder", null);
    }
}        return ResponseEntity.ok(new ApiResponse<>(true, "Stock added", saved));
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
