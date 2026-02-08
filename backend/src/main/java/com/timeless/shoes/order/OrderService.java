package com.timeless.shoes.order;

import com.timeless.shoes.common.exception.BusinessException;
import com.timeless.shoes.product.ProductVariant;
import com.timeless.shoes.product.ProductVariantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepo;
    private final OrderItemRepository orderItemRepo;
    private final ProductVariantRepository variantRepo;

    public OrderService(OrderRepository orderRepo,
                        OrderItemRepository orderItemRepo,
                        ProductVariantRepository variantRepo) {
        this.orderRepo = orderRepo;
        this.orderItemRepo = orderItemRepo;
        this.variantRepo = variantRepo;
    }

    /**
     * Create a new order with items and update stock
     */
    @Transactional
    public Order createOrder(Order order, List<OrderItem> items) {
        // Generate unique sale/order number
        order.setOrderNo("ORD-" + UUID.randomUUID().toString().substring(0, 8));
        order.setCreatedAt(LocalDateTime.now());

        BigDecimal total = BigDecimal.ZERO;

        for (OrderItem item : items) {
            ProductVariant variant = variantRepo.findById(item.getVariant().getId())
                    .orElseThrow(() -> new BusinessException("Variant not found"));

            // Check stock
            if (variant.getQuantity() < item.getQuantity()) {
                throw new BusinessException(
                    "Insufficient stock for " + variant.getProduct().getName() + " size " + variant.getSize()
                );
            }

            // Update variant stock
            variant.setQuantity(variant.getQuantity() - item.getQuantity());
            variantRepo.save(variant);

            // Calculate total per item
            BigDecimal itemTotal = item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            item.setTotal(itemTotal);
            item.setOrder(order);
            total = total.add(itemTotal);
        }

        order.setTotal(total);
        Order savedOrder = orderRepo.save(order);

        // Save all order items
        items.forEach(orderItemRepo::save);

        return savedOrder;
    }

    /**
     * Get today's total profit
     */
    public BigDecimal getTodayProfit() {
        return orderItemRepo.todayProfit();
    }

    /**
     * Payment summary by type
     */
    public List<Object[]> getPaymentSummary() {
        return orderRepo.paymentSummary();
    }

    /**
     * Low stock items
     */
    public List<ProductVariant> getLowStockVariants() {
        return variantRepo.findLowStockVariants();
    }
}
