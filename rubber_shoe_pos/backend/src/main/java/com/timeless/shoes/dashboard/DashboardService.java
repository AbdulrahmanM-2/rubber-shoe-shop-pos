package com.timeless.shoes.dashboard;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class DashboardService {

    public List<Map<String, Object>> getSalesSummary() {
        Map<String, Object> s = new HashMap<>();
        s.put("orderNumber", 0);
        s.put("total", BigDecimal.ZERO);
        return List.of(s);
    }

    public BigDecimal getTodayProfit() {
        return BigDecimal.ZERO;
    }
}
