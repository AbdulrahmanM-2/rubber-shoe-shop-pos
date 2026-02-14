package com.timeless.shoes.order;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    public Order createOrder(Order order) {
        // TODO: implement order creation logic
        return order;
    }

    public Optional<Order> getOrderById(Long id) {
        return Optional.empty();
    }

    public List<Order> getAllOrders() {
        return Collections.emptyList();
    }
}
