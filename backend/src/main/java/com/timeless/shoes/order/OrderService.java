package com.timeless.shoes.order;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
public class OrderService {

    public BigDecimal getTodayProfit() {
        return BigDecimal.ZERO;
    }

    public String getPaymentSummary() {
        return "No payments";
    }

    public List<String> getLowStockVariants() {
        return Collections.emptyList();
    }
}
