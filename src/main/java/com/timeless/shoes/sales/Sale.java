@Entity
public class Sale {

  @Id
  @GeneratedValue
  private Long id;

  private String saleNo;
  private BigDecimal total;
  private String paymentType;

  @ManyToOne
  private User user;

  private LocalDateTime createdAt = LocalDateTime.now();
}
