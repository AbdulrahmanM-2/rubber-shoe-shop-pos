package com.timeless.shoes.dashboard;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.*;
@Service
public class DashboardService {
    public Map<String, Object> metrics() {
        Map<String, Object> m = new HashMap<>();
        m.put("todaySales", BigDecimal.ZERO);
        m.put("todayProfit", BigDecimal.ZERO);
        m.put("lowStockItems", 0);
        m.put("totalCustomers", 0);
        return m;
    }
    public List<Map<String, Object>> getSalesSummary() {
        Map<String, Object> s = new HashMap<>();
        s.put("orderNumber", 0);
        s.put("total", BigDecimal.ZERO);
        return List.of(s);
    }
    public Map<String, Object> salesChart() {
        Map<String, Object> chart = new HashMap<>();
        chart.put("labels", List.of("Mon","Tue","Wed","Thu","Fri","Sat"));
        chart.put("sales", List.of(0,0,0,0,0,0));
        return chart;
    }
    public BigDecimal getTodayProfit() {
        return BigDecimal.ZERO;
    }
}
