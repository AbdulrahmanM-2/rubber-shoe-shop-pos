package com.timeless.shoes.service;

import com.timeless.shoes.entity.CashierShift;
import com.timeless.shoes.entity.Order;
import com.timeless.shoes.entity.User;
import com.timeless.shoes.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByShift(CashierShift shift) {
        return orderRepository.findByShift(shift);
    }

    public List<Order> getOrdersByCashier(User cashier) {
        return orderRepository.findByCashier(cashier);
    }

    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found: " + id));
    }
}
