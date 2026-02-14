package com.timeless.shoes.sales.dto;

import java.util.List;

public class CreateSaleRequest {

    private Long customerId;
    private String paymentType;
    private List<SaleItemDto> items;

    public CreateSaleRequest() {}

    public Long getCustomerId() { return customerId; }
    public String getPaymentType() { return paymentType; }
    public List<SaleItemDto> getItems() { return items; }

    public void setCustomerId(Long customerId) { this.customerId = customerId; }
    public void setPaymentType(String paymentType) { this.paymentType = paymentType; }
    public void setItems(List<SaleItemDto> items) { this.items = items; }
}
