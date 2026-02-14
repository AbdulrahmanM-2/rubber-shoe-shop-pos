package com.timeless.shoes.report;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DailySalesReport {
    private LocalDate date;
    private BigDecimal totalSales;
    private int totalOrders;

    public DailySalesReport() {
        this.date = LocalDate.now();
        this.totalSales = BigDecimal.ZERO;
        this.totalOrders = 0;
    }

    // Getters
    public LocalDate getDate() { return date; }
    public BigDecimal getTotalSales() { return totalSales; }
    public int getTotalOrders() { return totalOrders; }
    }
