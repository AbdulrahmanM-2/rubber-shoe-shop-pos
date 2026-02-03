package com.shop.report;

import java.math.BigDecimal;

public record DailySalesReport(
    BigDecimal totalSales,
    long totalTransactions
) {}
