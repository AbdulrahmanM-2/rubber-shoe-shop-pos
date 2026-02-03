package com.timeless.shoes.repository;

import com.timeless.shoes.model.Order;
import com.timeless.shoes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCashier(User cashier);

    List<Order> findByOrderTimeBetween(LocalDateTime start, LocalDateTime end);
}
