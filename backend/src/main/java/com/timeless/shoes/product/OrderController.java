package com.timeless.shoes.controller;

import com.timeless.shoes.model.Order;
import com.timeless.shoes.model.OrderItem;
import com.timeless.shoes.model.User;
import com.timeless.shoes.service.OrderService;
import com.timeless.shoes.service.UserService;
import com.timeless.shoes.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    @PostMapping
    public ApiResponse<Order> createOrder(
            @RequestParam String cashierPhone,
            @RequestBody List<OrderItem> items
    ) {
        User cashier = userService.getActiveUserByPhone(cashierPhone);
        Order order = orderService.createOrder(cashier, items);

        return ApiResponse.success("Order created", order);
    }
}
