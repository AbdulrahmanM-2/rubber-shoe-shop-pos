package com.timeless.shoes.report;

import java.math.BigDecimal;

/**
 * DTO for summarizing sales totals by payment type.
 */
public class CashMobileMoneyReport {

    private final BigDecimal cash;
    private final BigDecimal mobile;
    private final BigDecimal credit;

    /**
     * Constructor used by JPQL constructor expression in repository queries.
     *
     * @param cash   total of cash payments
     * @param mobile total of mobile payments
     * @param credit total of credit payments
     */
    public CashMobileMoneyReport(BigDecimal cash, BigDecimal mobile, BigDecimal credit) {
        this.cash = cash;
        this.mobile = mobile;
        this.credit = credit;
    }

    // Getters
    public BigDecimal getCash() {
        return cash;
    }

    public BigDecimal getMobile() {
        return mobile;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    @Override
    public String toString() {
        return "CashMobileMoneyReport{" +
                "cash=" + cash +
                ", mobile=" + mobile +
                ", credit=" + credit +
                '}';
    }
            }
