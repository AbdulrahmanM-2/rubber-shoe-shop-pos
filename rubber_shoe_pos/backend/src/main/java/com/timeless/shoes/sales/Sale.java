package com.timeless.shoes.sales;

import java.math.BigDecimal;
import java.util.List;
import com.timeless.shoes.customers.Customer;

public class Sale {
    private Long id;
    private String saleNo;
    private Customer customer;
    private BigDecimal total;
    private List<SaleItem> items;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSaleNo() { return saleNo; }
    public void setSaleNo(String saleNo) { this.saleNo = saleNo; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public List<SaleItem> getItems() { return items; }
    public void setItems(List<SaleItem> items) { this.items = items; }
}
