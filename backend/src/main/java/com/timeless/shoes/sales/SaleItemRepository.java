package com.timeless.shoes.repository;

import java.math.BigDecimal;
import java.util.List;

public interface SaleItemRepository {
    BigDecimal todayProfit();
    List<Object[]> dailyProfit();
}
