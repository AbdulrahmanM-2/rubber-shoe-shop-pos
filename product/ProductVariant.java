
@Entity
public class ProductVariant {

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  private Product product;

  private String size;
  private String color;
  private BigDecimal costPrice;
  private BigDecimal sellingPrice;
  private Integer quantity;
  private Integer reorderLevel;
}
