import com.timeless.shoes.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserService userService;

    public DataInitializer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create default admin if not exists
        try {
            userService.createUser("Admin User", "admin", "admin123", "OWNER");
            System.out.println("Default admin created: admin/admin123");
        } catch (Exception e) {
            System.out.println("Admin user already exists");
        }
    }
}
