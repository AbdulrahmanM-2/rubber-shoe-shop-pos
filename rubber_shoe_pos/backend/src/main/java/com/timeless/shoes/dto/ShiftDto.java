package com.timeless.shoes.dto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
public class ShiftDto {
    private Long id;
    private String cashierUsername;
    private Double openingBalance;
    private Double closingBalance;
    private Double systemTotal;
    private LocalDateTime openedAt;
    private LocalDateTime closedAt;
    private boolean closed;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCashierUsername() { return cashierUsername; }
    public void setCashierUsername(String u) { this.cashierUsername = u; }
    public Double getOpeningBalance() { return openingBalance; }
    public void setOpeningBalance(Double o) { this.openingBalance = o; }
    public Double getClosingBalance() { return closingBalance; }
    public void setClosingBalance(Double c) { this.closingBalance = c; }
    public Double getSystemTotal() { return systemTotal; }
    public void setSystemTotal(Double s) { this.systemTotal = s; }
    public LocalDateTime getOpenedAt() { return openedAt; }
    public void setOpenedAt(LocalDateTime o) { this.openedAt = o; }
    public LocalDateTime getClosedAt() { return closedAt; }
    public void setClosedAt(LocalDateTime c) { this.closedAt = c; }
    public boolean isClosed() { return closed; }
    public void setClosed(boolean c) { this.closed = c; }
}
