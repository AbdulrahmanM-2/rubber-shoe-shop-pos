package com.shop.stock;

import com.shop.product.ProductVariant;
import com.shop.product.VariantRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class StockService {

  private final VariantRepository variantRepo;
  private final StockMovementRepository stockRepo;

  public StockService(
      VariantRepository variantRepo,
      StockMovementRepository stockRepo
  ) {
    this.variantRepo = variantRepo;
    this.stockRepo = stockRepo;
  }

  @Transactional
  public void stockIn(Long variantId, Integer quantity, String ref) {

    ProductVariant v = variantRepo.findById(variantId).orElseThrow();
    v.setQuantity(v.getQuantity() + quantity);
    variantRepo.save(v);

    StockMovement sm = new StockMovement();
    sm.setVariant(v);
    sm.setType("IN");
    sm.setQuantity(quantity);
    sm.setReference(ref);
    stockRepo.save(sm);
  }

  @Transactional
  public void adjustStock(Long variantId, Integer quantity, String reason) {

    ProductVariant v = variantRepo.findById(variantId).orElseThrow();
    v.setQuantity(v.getQuantity() + quantity);
    variantRepo.save(v);

    StockMovement sm = new StockMovement();
    sm.setVariant(v);
    sm.setType("ADJUST");
    sm.setQuantity(quantity);
    sm.setReference(reason);
    stockRepo.save(sm);
  }
}
