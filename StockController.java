package com.shop.stock;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stock")
public class StockController {

  private final StockService stockService;

  public StockController(StockService stockService) {
    this.stockService = stockService;
  }

  @PostMapping("/in")
  public void stockIn(
      @RequestParam Long variantId,
      @RequestParam Integer quantity,
      @RequestParam String invoiceNo
  ) {
    stockService.stockIn(variantId, quantity, invoiceNo);
  }
}
