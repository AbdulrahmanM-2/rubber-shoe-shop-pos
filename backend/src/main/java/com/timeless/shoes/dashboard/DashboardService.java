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

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final SaleRepository saleRepo;
    private final SaleItemRepository saleItemRepo;
    private final ProductVariantRepository variantRepo;
    private final CustomerRepository customerRepo;

    /**
     * Dashboard metrics: sales, profit, low stock, total customers
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
     * Fetch recent orders for dashboard
     */
    public List<Map<String,Object>> recentOrders() {
        return saleRepo.recentOrders(PageRequest.of(0, 4))
                .stream()
                .map(s -> Map.of(
                    "order", s.getOrderNumber(),
                    "amount", s.getTotal()
                ))
                .toList();
    }

    /**
     * Generate sales chart data: daily sales and profit
     */
    public Map<String, Object> salesChart() {

        // Fetch raw data from repositories
        List<Object[]> salesData = saleRepo.dailySales();   // Example: Object[]{ "Mon", BigDecimal.valueOf(10000) }
        List<Object[]> profitData = saleItemRepo.dailyProfit();

        Map<String, BigDecimal> salesMap = new LinkedHashMap<>();
        Map<String, BigDecimal> profitMap = new LinkedHashMap<>();

        // Populate maps
        salesData.forEach(r -> salesMap.put(r[0].toString(), (BigDecimal) r[1]));
        profitData.forEach(r -> profitMap.put(r[0].toString(), (BigDecimal) r[1]));

        // Return data in frontend-friendly format
        return Map.of(
            "labels", salesMap.keySet(),
            "sales", salesMap.values(),
            "profit", profitMap.values()
        );
    }
                    }    }
}
    List<Object[]> sales = saleRepo.dailySales();
    List<Object[]> profit = saleItemRepo.dailyProfit();

    Map<String, BigDecimal> salesMap = new LinkedHashMap<>();
    Map<String, BigDecimal> profitMap = new LinkedHashMap<>();

    sales.forEach(r -> salesMap.put(
        r[0].toString(),
        (BigDecimal) r[1]
    ));

    profit.forEach(r -> profitMap.put(
        r[0].toString(),
        (BigDecimal) r[1]
    ));

    return Map.of(
        "labels", salesMap.keySet(),
        "sales", salesMap.values(),
        "profit", profitMap.values()
    );
    }
