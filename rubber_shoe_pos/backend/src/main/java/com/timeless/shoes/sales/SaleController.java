package com.timeless.shoes.sales;

import com.timeless.shoes.sales.dto.SaleDto;
import com.timeless.shoes.sales.dto.CreateSaleRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    /**
     * Create a new sale (cash, mobile, or credit)
     */
    @PostMapping
    public ResponseEntity<SaleDto> createSale(@RequestBody CreateSaleRequest request) {
        SaleDto sale = saleService.createSale(request);
        return ResponseEntity.ok(sale);
    }

    /**
     * List all sales (optionally can filter by date)
     */
    @GetMapping
    public ResponseEntity<List<SaleDto>> getAllSales(
            @RequestParam(value = "date", required = false) String date
    ) {
        List<SaleDto> sales = saleService.getAllSales(date);
        return ResponseEntity.ok(sales);
    }

    /**
     * Get single sale details
     */
    @GetMapping("/{saleId}")
    public ResponseEntity<SaleDto> getSale(@PathVariable Long saleId) {
        SaleDto sale = saleService.getSaleById(saleId);
        return ResponseEntity.ok(sale);
    }
}
