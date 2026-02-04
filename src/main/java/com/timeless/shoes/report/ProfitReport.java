@Query("""
    SELECT new com.timeless.shoes.report.ProfitReport(
        CURRENT_DATE,
        COALESCE(SUM((si.unitPrice - v.costPrice) * si.quantity), 0)
    )
    FROM SaleItem si
    JOIN si.variant v
    WHERE DATE(si.sale.createdAt) = CURRENT_DATE
""")
ProfitReport todayProfit();
