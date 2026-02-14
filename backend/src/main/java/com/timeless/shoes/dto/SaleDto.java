package com.timeless.shoes.sales.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SaleDto {
    private Long id;
    private String saleNo;
    private BigDecimal total;
    private String paymentType;
    private LocalDateTime createdAt;
}
