package com.timeless.shoes.sales.dto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
public class SaleDto {
    private Long saleId;
    private String saleNo;
    private String customerName;
    private String paymentType;
    private BigDecimal total;
    private LocalDateTime createdAt;
    private List<SaleItemDto> items;
    public Long getSaleId() { return saleId; }
    public void setSaleId(Long id) { this.saleId = id; }
    public String getSaleNo() { return saleNo; }
    public void setSaleNo(String s) { this.saleNo = s; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String c) { this.customerName = c; }
    public String getPaymentType() { return paymentType; }
    public void setPaymentType(String p) { this.paymentType = p; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal t) { this.total = t; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime c) { this.createdAt = c; }
    public List<SaleItemDto> getItems() { return items; }
    public void setItems(List<SaleItemDto> i) { this.items = i; }
}
