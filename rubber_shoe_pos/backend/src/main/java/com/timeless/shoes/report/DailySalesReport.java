package com.timeless.shoes.report;

import java.math.BigDecimal;

public class DailySalesReport {
    private String product;
    private BigDecimal total;

    public String getProduct() { return product; }
    public void setProduct(String product) { this.product = product; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
}
