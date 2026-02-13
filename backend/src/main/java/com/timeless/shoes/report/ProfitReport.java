package com.timeless.shoes.report;

import java.math.BigDecimal;

public class ProfitReport {

    private BigDecimal totalProfit;
    private BigDecimal totalSales;

    // Default constructor required by JPA when using "new" in queries
    public ProfitReport() {
    }

    // Constructor for projection
    public ProfitReport(BigDecimal totalProfit, BigDecimal totalSales) {
        this.totalProfit = totalProfit;
        this.totalSales = totalSales;
    }

    // Getters and Setters
    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
    }

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
    }

    @Override
    public String toString() {
        return "ProfitReport{" +
                "totalProfit=" + totalProfit +
                ", totalSales=" + totalSales +
                '}';
    }
}
