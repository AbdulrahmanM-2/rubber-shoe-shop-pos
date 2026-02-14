package com.timeless.shoes.sales.dto;

import lombok.Data;
import java.util.List;

@Data
public class CreateSaleRequest {
    private Long customerId;
    private String paymentType;
    private List<SaleItemDto> items;
}
