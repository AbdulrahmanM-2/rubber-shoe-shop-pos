package com.timeless.shoes.dto;

import java.math.BigDecimal;
import java.util.List;

public class OrderDto {

    private Long id;
    private String orderNumber;
    private BigDecimal total;
    private List<Object> items; // simplified

    public OrderDto() {}

    public Long getId() { return id; }
    public String getOrderNumber() { return orderNumber; }
    public BigDecimal getTotal() { return total; }
    public List<Object> getItems() { return items; }

    public void setId(Long id) { this.id = id; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public void setItems(List<Object> items) { this.items = items; }
        }
