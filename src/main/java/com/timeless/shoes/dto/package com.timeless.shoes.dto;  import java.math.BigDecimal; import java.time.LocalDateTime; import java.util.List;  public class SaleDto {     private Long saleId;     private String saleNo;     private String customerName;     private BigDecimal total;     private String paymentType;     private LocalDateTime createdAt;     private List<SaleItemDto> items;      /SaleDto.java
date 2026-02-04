package com.timeless.shoes.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class SaleDto {
    private Long saleId;
    private String saleNo;
    private String customerName;
    private BigDecimal total;
    private String paymentType;
    private LocalDateTime createdAt;
    private List<SaleItemDto> items;

    // Getters & setters
}
