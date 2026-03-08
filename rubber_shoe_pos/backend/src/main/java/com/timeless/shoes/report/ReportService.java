package com.timeless.shoes.report;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class ReportService {
    public DailySalesReport todaySales() {
        DailySalesReport r = new DailySalesReport();
        r.setProduct("All");
        r.setTotal(BigDecimal.ZERO);
        return r;
    }
}
