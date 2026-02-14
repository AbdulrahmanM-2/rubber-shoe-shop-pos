package com.timeless.shoes.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailySalesReport {
    private LocalDate date;
    private BigDecimal totalSales;
    private BigDecimal totalProfit;
}
