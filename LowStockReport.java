@Query("""
 SELECT v
 FROM ProductVariant v
 WHERE v.quantity <= v.reorderLevel
 ORDER BY v.quantity ASC
""")
List<ProductVariant> lowStock();
