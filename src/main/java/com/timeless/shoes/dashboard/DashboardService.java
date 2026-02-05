package com.timeless.shoes.dashboard;

import lombok.RequiredArgsConstructor;
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

    public Map<String, Object> metrics() {
        return Map.of(
            "todaySales", saleRepo.todaySales(),
            "todayProfit", saleItemRepo.todayProfit(),
            "lowStockItems", variantRepo.lowStockCount(),
            "totalCustomers", customerRepo.count()
        );
    }

    public List<Map<String,Object>> recentOrders() {
        return saleRepo.recentOrders(PageRequest.of(0, 4))
                .stream()
                .map(s -> Map.of(
                    "order", s.getOrderNumber(),
                    "amount", s.getTotal()
                ))
                .toList();
    }
}
public Map<String, Object> salesChart() {

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
