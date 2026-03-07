package com.timeless.shoes.stock;

import com.timeless.shoes.product.ProductVariant;
import com.timeless.shoes.product.ProductVariantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {

    private final StockMovementRepository stockRepo;
    private final ProductVariantRepository variantRepo;

    public StockService(StockMovementRepository stockRepo, ProductVariantRepository variantRepo) {
        this.stockRepo = stockRepo;
        this.variantRepo = variantRepo;
    }

    @Transactional
    public void addStock(ProductVariant variant, int quantity, String reference) {
        variant.setQuantity(variant.getQuantity() + quantity);
        variantRepo.save(variant);

        StockMovement movement = new StockMovement(variant, "IN", quantity, reference);
        stockRepo.save(movement);
    }

    @Transactional
    public void removeStock(ProductVariant variant, int quantity, String reference) {
        if (variant.getQuantity() < quantity) {
            throw new RuntimeException("Insufficient stock for " + variant.getProduct().getName());
        }

        variant.setQuantity(variant.getQuantity() - quantity);
        variantRepo.save(variant);

        StockMovement movement = new StockMovement(variant, "OUT", quantity, reference);
        stockRepo.save(movement);
    }

    @Transactional
    public void adjustStock(ProductVariant variant, int quantity, String reference) {
        variant.setQuantity(quantity);
        variantRepo.save(variant);

        StockMovement movement = new StockMovement(variant, "ADJUST", quantity, reference);
        stockRepo.save(movement);
    }
}
