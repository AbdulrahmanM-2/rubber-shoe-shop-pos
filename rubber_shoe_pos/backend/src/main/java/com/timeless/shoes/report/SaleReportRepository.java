package com.timeless.shoes.report;

import org.springframework.stereotype.Repository;
import java.util.Collections;
import java.util.List;

@Repository
public class SaleReportRepository {
    public List<DailySalesReport> todaySales() {
        return Collections.emptyList();
    }

    public double todayProfit() {
        return 0.0;
    }

    public Object paymentSummary() {
        return null;
    }
}
