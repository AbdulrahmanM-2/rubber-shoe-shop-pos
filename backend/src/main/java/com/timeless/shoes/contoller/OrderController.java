package com.timeless.shoes.controller;

import com.timeless.shoes.dto.OrderDto;
import com.timeless.shoes.dto.CreateOrderRequest;
import com.timeless.shoes.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * Record a new order/sale
     * Only cashiers can record orders
     */
    @PostMapping
    @PreAuthorize("hasRole('CASHIER')")
    public ResponseEntity<OrderDto> createOrder(@RequestBody CreateOrderRequest request) {
        OrderDto order = orderService.createOrder(request);
        return ResponseEntity.ok(order);
    }

    /**
     * Get all orders for the currently active shift
     * Only cashiers can view their shift orders
     */
    @GetMapping("/shift/active")
    @PreAuthorize("hasRole('CASHIER')")
    public ResponseEntity<List<OrderDto>> getOrdersForActiveShift() {
        List<OrderDto> orders = orderService.getOrdersForActiveShift();
        return ResponseEntity.ok(orders);
    }

    /**
     * Manager endpoint: get all orders
     */
    @GetMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    /**
     * Manager endpoint: get orders for a specific shift
     */
    @GetMapping("/shift/{shiftId}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<List<OrderDto>> getOrdersByShift(@PathVariable Long shiftId) {
        List<OrderDto> orders = orderService.getOrdersByShift(shiftId);
        return ResponseEntity.ok(orders);
    }
  }
