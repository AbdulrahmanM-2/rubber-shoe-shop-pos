@Entity
public class StockMovement {

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  private ProductVariant variant;

  private String type; // IN, OUT
  private Integer quantity;
  private String reference;

  private LocalDateTime createdAt = LocalDateTime.now();
}
