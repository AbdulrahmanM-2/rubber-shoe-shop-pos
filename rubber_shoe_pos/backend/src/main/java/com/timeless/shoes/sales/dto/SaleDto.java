package com.timeless.shoes.sales.dto;

import java.math.BigDecimal;
import java.util.List;

public class SaleDto {
    private Long saleId;
    private String saleNo;
    private Long customerId;
    private String customerName;
    private BigDecimal total;
    private List<SaleItemDto> items;

    public Long getSaleId() { return saleId; }
    public void setSaleId(Long saleId) { this.saleId = saleId; }

    public String getSaleNo() { return saleNo; }
    public void setSaleNo(String saleNo) { this.saleNo = saleNo; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public List<SaleItemDto> getItems() { return items; }
    public void setItems(List<SaleItemDto> items) { this.items = items; }
        }
