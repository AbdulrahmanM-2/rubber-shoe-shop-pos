package com.timeless.shoes.report;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DailySalesReport {

    private LocalDate date;
    private BigDecimal totalSales;
    private BigDecimal profit;

    public DailySalesReport() {}

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public BigDecimal getTotalSales() { return totalSales; }
    public void setTotalSales(BigDecimal totalSales) { this.totalSales = totalSales; }

    public BigDecimal getProfit() { return profit; }
    public void setProfit(BigDecimal profit) { this.profit = profit; }
}
