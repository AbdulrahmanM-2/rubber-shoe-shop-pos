@Query("""
    SELECT new com.timeless.shoes.report.CashMobileMoneyReport(
        SUM(CASE WHEN s.paymentType = 'CASH' THEN s.total ELSE 0 END),
        SUM(CASE WHEN s.paymentType = 'MOBILE' THEN s.total ELSE 0 END),
        SUM(CASE WHEN s.paymentType = 'CREDIT' THEN s.total ELSE 0 END)
    )
    FROM Sale s
    WHERE DATE(s.createdAt) = CURRENT_DATE
""")
CashMobileMoneyReport paymentSummary();
