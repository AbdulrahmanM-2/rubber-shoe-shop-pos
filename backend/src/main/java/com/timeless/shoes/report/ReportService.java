package com.timeless.shoes.report;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

    public List<DailySalesReport> todaySales() { return new ArrayList<>(); }

    public BigDecimal todayProfit() { return BigDecimal.ZERO; }

    public Object paymentSummary() { return new Object(); }
}
