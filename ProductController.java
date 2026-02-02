package com.shop.product;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductRepository productRepo;

  public ProductController(ProductRepository productRepo) {
    this.productRepo = productRepo;
  }

  @PostMapping
  public Product create(@RequestBody Product product) {
    return productRepo.save(product);
  }

  @GetMapping
  public List<Product> all() {
    return productRepo.findAll();
  }

  @GetMapping("/{id}")
  public Product get(@PathVariable Long id) {
    return productRepo.findById(id).orElseThrow();
  }
}
