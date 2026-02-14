package com.timeless.shoes.report;

import java.math.BigDecimal;

/**
 * DTO for profit report.
 */
public class ProfitReport {

    private BigDecimal totalProfit;
    private BigDecimal totalSales;

    // Default constructor for JPA
    public ProfitReport() {}

    // Constructor for projections
    public ProfitReport(BigDecimal totalProfit, BigDecimal totalSales) {
        this.totalProfit = totalProfit;
        this.totalSales = totalSales;
    }

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
