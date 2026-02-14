package com.timeless.shoes.dto;

import java.math.BigDecimal;

public class CloseShiftRequest {

    private BigDecimal closingBalance;

    public BigDecimal getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(BigDecimal closingBalance) {
        this.closingBalance = closingBalance;
    }
}
