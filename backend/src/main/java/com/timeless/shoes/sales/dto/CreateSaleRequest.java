package com.timeless.shoes.sales.dto;

import java.util.List;

public class CreateSaleRequest {

    public String getPaymentType() { return "CASH"; }
    public Long getCustomerId() { return 1L; }
    public List<SaleItemDto> getItems() { return List.of(new SaleItemDto()); }
}
