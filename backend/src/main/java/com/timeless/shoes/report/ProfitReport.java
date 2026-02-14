package com.timeless.shoes.report;

import java.math.BigDecimal;

/**
 * DTO for profit reports.
 */
public class ProfitReport {

    private BigDecimal totalProfit;
    private BigDecimal totalSales;

    // Default constructor (needed for JPA projections)
    public ProfitReport() {
    }

    // Constructor for JPA projection
    public ProfitReport(BigDecimal totalProfit, BigDecimal totalSales) {
        this.totalProfit = totalProfit;
        this.totalSales = totalSales;
    }

    // Getters and setters
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

} // <-- ONLY closing brace for the class    @Override
    public String toString() {
        return "ProfitReport{" +
                "totalProfit=" + totalProfit +
                ", totalSales=" + totalSales +
                '}';
    }

} // <-- final class closing brace    @Override
    public String toString() {
        return "ProfitReport{" +
                "totalProfit=" + totalProfit +
                ", totalSales=" + totalSales +
                '}';
    }
}    @Override
    public String toString() {
        return "ProfitReport{" +
                "totalProfit=" + totalProfit +
                ", totalSales=" + totalSales +
                '}';
    }
}
