package com.timeless.shoes.report;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DailySalesReport {
    private LocalDate date;
    private BigDecimal totalSales;

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public BigDecimal getTotalSales() { return totalSales; }
    public void setTotalSales(BigDecimal totalSales) { this.totalSales = totalSales; }
}
