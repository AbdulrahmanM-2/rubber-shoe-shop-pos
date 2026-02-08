package com.timeless.shoes.controller.order;

import com.timeless.shoes.dto.ApiResponse;
import com.timeless.shoes.order.Order;
import com.timeless.shoes.order.OrderService;
import com.timeless.shoes.product.ProductVariant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Order>> createOrder(@RequestBody Order order) {
        Order savedOrder = orderService.createOrder(order, order.getItems());
        return ResponseEntity.ok(new ApiResponse<>(true, "Order created successfully", savedOrder));
    }

    @GetMapping("/profit")
    public ResponseEntity<ApiResponse<BigDecimal>> todayProfit() {
        BigDecimal profit = orderService.getTodayProfit();
        return ResponseEntity.ok(new ApiResponse<>(true, "Today’s profit retrieved", profit));
    }

    @GetMapping("/payments")
    public ResponseEntity<ApiResponse<List<Object[]>>> paymentSummary() {
        List<Object[]> summary = orderService.getPaymentSummary();
        return ResponseEntity.ok(new ApiResponse<>(true, "Payment summary retrieved", summary));
    }

    @GetMapping("/low-stock")
    public ResponseEntity<ApiResponse<List<ProductVariant>>> lowStock() {
        List<ProductVariant> variants = orderService.getLowStockVariants();
        return ResponseEntity.ok(new ApiResponse<>(true, "Low-stock variants retrieved", variants));
    }
}
