@Entity
public class Product {

  @Id
  @GeneratedValue
  private Long id;

  private String name;
  private String brand;
  private String gender;
  private String category;
}
