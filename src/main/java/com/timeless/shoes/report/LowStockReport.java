@Query("""
    SELECT new com.timeless.shoes.report.LowStockReport(
        v.id,
        v.product.name,
        v.size,
        v.color,
        v.sellingPrice,
        v.quantity,
        v.reorderLevel
    )
    FROM ProductVariant v
    WHERE v.quantity <= v.reorderLevel
    ORDER BY v.quantity ASC
""")
List<LowStockReport> lowStock();
