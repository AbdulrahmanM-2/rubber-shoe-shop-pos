package com.timeless.shoes.service;

import com.timeless.shoes.model.*;
import com.timeless.shoes.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Transactional
    public Order createOrder(User cashier, List<OrderItem> items) {

        BigDecimal total = BigDecimal.ZERO;

        for (OrderItem item : items) {
            Product product = item.getProduct();

            productService.reduceStock(product, item.getQuantity());

            BigDecimal lineTotal =
                    product.getSellingPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            item.setPrice(lineTotal);

            total = total.add(lineTotal);
        }

        Order order = Order.builder()
                .cashier(cashier)
                .orderTime(LocalDateTime.now())
                .totalAmount(total)
                .items(items)
                .build();

        items.forEach(i -> i.setOrder(order));

        return orderRepository.save(order);
    }
              }
