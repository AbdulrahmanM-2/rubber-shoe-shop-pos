package com.shop.product;

import com.shop.stock.StockService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/variants")
public class VariantController {

  private final VariantRepository variantRepo;
  private final StockService stockService;

  public VariantController(
      VariantRepository variantRepo,
      StockService stockService
  ) {
    this.variantRepo = variantRepo;
    this.stockService = stockService;
  }

  @PostMapping
  public ProductVariant create(@RequestBody ProductVariant variant) {
    return variantRepo.save(variant);
  }

  @GetMapping
  public List<ProductVariant> all() {
    return variantRepo.findAll();
  }

  // Price update (manager only)
  @PutMapping("/{id}/price")
  public ProductVariant updatePrice(
      @PathVariable Long id,
      @RequestParam BigDecimal sellingPrice
  ) {
    ProductVariant v = variantRepo.findById(id).orElseThrow();
    v.setSellingPrice(sellingPrice);
    return variantRepo.save(v);
  }

  // Manual stock adjustment
  @PutMapping("/{id}/adjust-stock")
  public void adjustStock(
      @PathVariable Long id,
      @RequestParam Integer quantity,
      @RequestParam String reason
  ) {
    stockService.adjustStock(id, quantity, reason);
  }
}
