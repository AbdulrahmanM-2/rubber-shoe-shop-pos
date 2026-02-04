package com.timeless.shoes.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProfitReportDto {
    private LocalDate reportDate;
    private BigDecimal profit;

    public ProfitReportDto(LocalDate reportDate, BigDecimal profit) {
        this.reportDate = reportDate;
        this.profit = profit;
    }

    // Getters & setters
}
