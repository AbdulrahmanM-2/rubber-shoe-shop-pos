package com.timeless.shoes.order.dto;

import java.math.BigDecimal;
import java.util.List;

public class OrderDto {
    private Long id;
    private List<String> items;
    private BigDecimal total;
    private BigDecimal profit;
    private String paymentSummary;

    public OrderDto() {}

    public OrderDto(Long id, List<String> items, BigDecimal total, BigDecimal profit, String paymentSummary) {
        this.id = id;
        this.items = items;
        this.total = total;
        this.profit = profit;
        this.paymentSummary = paymentSummary;
    }

    // Getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public List<String> getItems() { return items; }
    public void setItems(List<String> items) { this.items = items; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public BigDecimal getProfit() { return profit; }
    public void setProfit(BigDecimal profit) { this.profit = profit; }

    public String getPaymentSummary() { return paymentSummary; }
    public void setPaymentSummary(String paymentSummary) { this.paymentSummary = paymentSummary; }
}
