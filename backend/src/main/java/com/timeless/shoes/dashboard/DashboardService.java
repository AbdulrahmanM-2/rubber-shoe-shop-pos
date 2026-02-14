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
 * Service for dashboard metrics, recent orders, and sales charts.
 */
@Service
@RequiredArgsConstructor
public class DashboardService {

    private final SaleRepository saleRepo;
    private final SaleItemRepository saleItemRepo;
    private final ProductVariantRepository variantRepo;
    private final CustomerRepository customerRepo;

    /**
     * Get dashboard metrics: today’s sales, profit, low stock, and total customers.
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
     * Get the 4 most recent orders for dashboard display.
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
     * Get sales and profit chart data for the dashboard.
     */
    public Map<String, Object> salesChart() {
        // Fetch raw data from repositories
        List<Object[]> salesData = saleRepo.dailySales();
        List<Object[]> profitData = saleItemRepo.dailyProfit();

        // Prepare maps to preserve order
        Map<String, BigDecimal> salesMap = new LinkedHashMap<>();
        Map<String, BigDecimal> profitMap = new LinkedHashMap<>();

        // Populate sales map
        for (Object[] row : salesData) {
            String label = row[0].toString();
            BigDecimal value = (BigDecimal) row[1];
            salesMap.put(label, value);
        }

        // Populate profit map
        for (Object[] row : profitData) {
            String label = row[0].toString();
            BigDecimal value = (BigDecimal) row[1];
            profitMap.put(label, value);
        }

        // Return combined map
        return Map.of(
            "labels", salesMap.keySet(),
            "sales", salesMap.values(),
            "profit", profitMap.values()
        );
    }

} // final class closing brace                .map(s -> Map.of(
                    "order", s.getOrderNumber(),
                    "amount", s.getTotal()
                ))
                .toList();
    }

    /** Sales chart data */
    public Map<String, Object> salesChart() {
        List<Object[]> salesData = saleRepo.dailySales();
        List<Object[]> profitData = saleItemRepo.dailyProfit();

        Map<String, BigDecimal> salesMap = new LinkedHashMap<>();
        Map<String, BigDecimal> profitMap = new LinkedHashMap<>();

        salesData.forEach(r -> salesMap.put(r[0].toString(), (BigDecimal) r[1]));
        profitData.forEach(r -> profitMap.put(r[0].toString(), (BigDecimal) r[1]));

        return Map.of(
            "labels", salesMap.keySet(),
            "sales", salesMap.values(),
            "profit", profitMap.values()
        );
    }

} // final class closing brace                    "amount", s.getTotal()
                ))
                .toList();
    }

    public Map<String, Object> salesChart() {

        // ✅ Everything inside this method
        List<Object[]> salesData = saleRepo.dailySales();
        List<Object[]> profitData = saleItemRepo.dailyProfit();

        Map<String, BigDecimal> salesMap = new LinkedHashMap<>();
        Map<String, BigDecimal> profitMap = new LinkedHashMap<>();

        salesData.forEach(r -> salesMap.put(r[0].toString(), (BigDecimal) r[1]));
        profitData.forEach(r -> profitMap.put(r[0].toString(), (BigDecimal) r[1]));

        return Map.of(
            "labels", salesMap.keySet(),
            "sales", salesMap.values(),
            "profit", profitMap.values()
        );
    }

} // <-- final class closing brace     */
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
