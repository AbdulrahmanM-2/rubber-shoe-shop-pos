package com.timeless.shoes.dashboard;

import com.timeless.shoes.repository.SaleItemRepository;
import com.timeless.shoes.repository.SaleRepository;
import com.timeless.shoes.repository.ProductVariantRepository;
import com.timeless.shoes.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * DashboardService provides metrics, recent orders, and sales chart data.
 */
@Service
@RequiredArgsConstructor
public class DashboardService {

    private final SaleRepository saleRepo;
    private final SaleItemRepository saleItemRepo;
    private final ProductVariantRepository variantRepo;
    private final CustomerRepository customerRepo;

    /**
     * Get main dashboard metrics.
     */
    public Map<String, Object> metrics() {
        return Map.of(
                "todaySales", saleRepo.todaySales(),
                "todayProfit", saleItemRepo.todayProfit(),
                "lowStockItems", variantRepo.lowStockCount(),
                "totalCustomers", customerRepo.count()
        );
    }

    /**
     * Get 4 most recent orders.
     */
    public List<Map<String, Object>> recentOrders() {
        return saleRepo.recentOrders(PageRequest.of(0, 4))
                .stream()
                .map(s -> Map.of(
                        "order", s.getOrderNumber(),
                        "amount", s.getTotal()
                ))
                .toList();
    }

    /**
     * Get sales and profit chart data for dashboard.
     */
    public Map<String, Object> salesChart() {
        List<Object[]> salesData = saleRepo.dailySales();
        List<Object[]> profitData = saleItemRepo.dailyProfit();

        Map<String, BigDecimal> salesMap = new LinkedHashMap<>();
        Map<String, BigDecimal> profitMap = new LinkedHashMap<>();

        for (Object[] row : salesData) {
            String label = row[0].toString();
            BigDecimal value = (BigDecimal) row[1];
            salesMap.put(label, value);
        }

        for (Object[] row : profitData) {
            String label = row[0].toString();
            BigDecimal value = (BigDecimal) row[1];
            profitMap.put(label, value);
        }

        return Map.of(
                "labels", salesMap.keySet(),
                "sales", salesMap.values(),
                "profit", profitMap.values()
        );
    }

} // final class closing brace – nothing after this
