package com.timeless.shoes.dashboard;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin
public class DashboardController {

    @GetMapping("/metrics")
    public Map<String, Object> metrics() {
        return Map.of(
            "todaySales", 35500,
            "todayProfit", 12000,
            "lowStockItems", 8,
            "totalCustomers", 145
        );
    }

    @GetMapping("/sales-overview")
    public Map<String, Object> salesOverview() {
        return Map.of(
            "labels", List.of("Mon","Tue","Wed","Thu","Fri","Sat"),
            "sales", List.of(10000,20000,15000,25000,22000,35500),
            "profit", List.of(5000,15000,10000,20000,15000,28000)
        );
    }

    @GetMapping("/stock-summary")
    public Map<String, Object> stockSummary() {
        return Map.of(
            "inStock", 320,
            "outOfStock", 45
        );
    }

    @GetMapping("/recent-orders")
    public List<Map<String,Object>> recentOrders() {
        return List.of(
            Map.of("order","OR-202604-005","amount",12500),
            Map.of("order","OR-202604-004","amount",8000),
            Map.of("order","OR-202604-003","amount",5000),
            Map.of("order","OR-202604-002","amount",10000)
        );
    }

    // ✅ Correctly inside the class
    @GetMapping("/sales-chart")
    public Map<String, Object> salesChart() {
        return service.salesChart();
    }
                }
