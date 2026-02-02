package com.shop.sales;

import com.shop.product.ProductVariant;
import com.shop.product.VariantRepository;
import com.shop.stock.StockMovement;
import com.shop.stock.StockMovementRepository;
import com.shop.user.User;
import com.shop.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class SaleService {

  private final VariantRepository variantRepo;
  private final SaleRepository saleRepo;
  private final SaleItemRepository saleItemRepo;
  private final StockMovementRepository stockRepo;
  private final UserRepository userRepo;

  public SaleService(
      VariantRepository variantRepo,
      SaleRepository saleRepo,
      SaleItemRepository saleItemRepo,
      StockMovementRepository stockRepo,
      UserRepository userRepo
  ) {
    this.variantRepo = variantRepo;
    this.saleRepo = saleRepo;
    this.saleItemRepo = saleItemRepo;
    this.stockRepo = stockRepo;
    this.userRepo = userRepo;
  }

  @Transactional
  public Sale createSale(SaleRequest request) {

    String username = SecurityContextHolder.getContext()
        .getAuthentication().getName();

    User cashier = userRepo.findByUsername(username)
        .orElseThrow();

    Sale sale = new Sale();
    sale.setSaleNo("SALE-" + UUID.randomUUID());
    sale.setPaymentType(request.paymentType);
    sale.setUser(cashier);

    BigDecimal total = BigDecimal.ZERO;

    sale = saleRepo.save(sale);

    for (SaleItemRequest itemReq : request.items) {

      ProductVariant variant = variantRepo.findById(itemReq.variantId)
          .orElseThrow(() -> new RuntimeException("Item not found"));

      if (variant.getQuantity() < itemReq.quantity) {
        throw new RuntimeException(
            "Not enough stock for size " + variant.getSize()
        );
      }

      // Deduct stock
      variant.setQuantity(variant.getQuantity() - itemReq.quantity);
      variantRepo.save(variant);

      BigDecimal lineTotal =
          variant.getSellingPrice()
              .multiply(BigDecimal.valueOf(itemReq.quantity));

      SaleItem item = new SaleItem();
      item.setSale(sale);
      item.setVariant(variant);
      item.setQuantity(itemReq.quantity);
      item.setUnitPrice(variant.getSellingPrice());
      item.setTotal(lineTotal);

      saleItemRepo.save(item);

      // Stock movement log
      StockMovement sm = new StockMovement();
      sm.setVariant(variant);
      sm.setType("OUT");
      sm.setQuantity(itemReq.quantity);
      sm.setReference(sale.getSaleNo());
      stockRepo.save(sm);

      total = total.add(lineTotal);
    }

    sale.setTotal(total);
    return saleRepo.save(sale);
  }
}
