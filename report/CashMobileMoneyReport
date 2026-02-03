@Query("""
 SELECT s.paymentType, SUM(s.total)
 FROM Sale s
 WHERE DATE(s.createdAt) = CURRENT_DATE
 GROUP BY s.paymentType
""")
List<Object[]> paymentSummary();
