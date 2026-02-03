@Entity
@Table(name = "users")
@Getter @Setter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String fullName;
  private String username;
  private String passwordHash;
  private String role; // OWNER, MANAGER, CASHIER
  private boolean active = true;
}
