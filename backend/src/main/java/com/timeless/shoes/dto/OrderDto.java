package com.timeless.shoes.order.dto;

import java.util.List;

public class OrderDto {
    private Long id;
    private List<Object[]> items; // stub
    private List<Object> lowStockVariants;

    public Long getId() { return id; }
    public List<Object[]> getItems() { return items; }
    public void setItems(List<Object[]> items) { this.items = items; }
    public List<Object> getLowStockVariants() { return lowStockVariants; }
}
