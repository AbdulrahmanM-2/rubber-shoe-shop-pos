package com.shop.sales;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

  private final SaleService saleService;

  public SaleController(SaleService saleService) {
    this.saleService = saleService;
  }

  @PostMapping
  public Sale create(@RequestBody SaleRequest request) {
    return saleService.createSale(request);
  }
}
