package com.timeless.shoes.report;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

public class ReportService {
    public BigDecimal todaySales() {
        return BigDecimal.ZERO;
    }

    public BigDecimal todayProfit() {
        return BigDecimal.ZERO;
    }

    public List<Object[]> paymentSummary() {
        return new ArrayList<>();
    }
}
