package com.timeless.shoes.report;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DailySalesReport {

    private LocalDate date;
    private BigDecimal totalSales;
    private BigDecimal totalProfit;

    public DailySalesReport() {}

    public LocalDate getDate() { return date; }
    public BigDecimal getTotalSales() { return totalSales; }
    public BigDecimal getTotalProfit() { return totalProfit; }

    public void setDate(LocalDate date) { this.date = date; }
    public void setTotalSales(BigDecimal totalSales) { this.totalSales = totalSales; }
    public void setTotalProfit(BigDecimal totalProfit) { this.totalProfit = totalProfit; }
}
