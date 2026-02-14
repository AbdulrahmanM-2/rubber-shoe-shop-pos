package com.timeless.shoes.repository;

import java.math.BigDecimal;
import java.util.List;

public interface SaleRepository {
    BigDecimal todaySales();
    List<Object[]> dailySales();
    List<Object[]> recentOrders(org.springframework.data.domain.Pageable pageable);
}
