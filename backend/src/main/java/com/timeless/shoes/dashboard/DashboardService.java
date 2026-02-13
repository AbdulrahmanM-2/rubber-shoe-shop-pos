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
     * Dashboard metrics
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
     * Recent orders for dashboard
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
     * Sales chart data
     */
    public Map<String, Object> salesChart() {

        // Fetch raw data from repositories
        List<Object[]> salesData = saleRepo.dailySales();     // Example: Object[]{ "Mon", BigDecimal.valueOf(10000) }
        List<Object[]> profitData = saleItemRepo.dailyProfit();

        // Map day -> value
        Map<String, BigDecimal> salesMap = new LinkedHashMap<>();
        Map<String, BigDecimal> profitMap = new LinkedHashMap<>();

        salesData.forEach(r -> salesMap.put(
                r[0].toString(),
                (BigDecimal) r[1]
        ));

        profitData.forEach(r -> profitMap.put(
                r[0].toString(),
                (BigDecimal) r[1]
        ));

        // Return in frontend-friendly format
        return Map.of(
            "labels", salesMap.keySet(),
            "sales", salesMap.values(),
            "profit", profitMap.values()
        );
    }
            }            BigDecimal.valueOf(22000),
            BigDecimal.valueOf(35500)
        );
        List<BigDecimal> profit = List.of(
            BigDecimal.valueOf(5000),
            BigDecimal.valueOf(15000),
            BigDecimal.valueOf(10000),
            BigDecimal.valueOf(20000),
            BigDecimal.valueOf(15000),
            BigDecimal.valueOf(28000)
        );

        return Map.of(
            "labels", labels,
            "sales", sales,
            "profit", profit
        );
    }
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
