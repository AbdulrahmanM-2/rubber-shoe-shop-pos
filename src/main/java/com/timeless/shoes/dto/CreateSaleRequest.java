package com.timeless.shoes.dto;

import java.util.List;

public class CreateSaleRequest {
    private Long customerId; // optional for walk-in
    private String paymentType;
    private List<SaleItemDto> items;

    // Getters & setters
}
