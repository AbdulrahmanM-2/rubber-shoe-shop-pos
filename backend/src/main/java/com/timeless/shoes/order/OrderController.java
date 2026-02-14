package com.timeless.shoes.controller.order;

import com.timeless.shoes.order.OrderService;
import com.timeless.shoes.order.dto.OrderDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Collections;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders/todayProfit")
    public String todayProfit() {
        return orderService.getTodayProfit().toString();
    }

    @GetMapping("/orders/paymentSummary")
    public String paymentSummary() {
        return orderService.getPaymentSummary();
    }

    @GetMapping("/orders/lowStock")
    public List<String> lowStockVariants() {
        return orderService.getLowStockVariants();
    }
}
