package PackUsers;

import enums.Enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DatabaseInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Sprawdź czy admin już istnieje
        if (userRepository.findByEmail("admin@example.com").isEmpty()) {
            // Tworzenie użytkownika admin (tylko podczas pierwszego uruchomienia aplikacji)
            User admin = new User();
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("admin123")); // Hashowanie hasła
            admin.setName("Administrator Systemu");
            admin.setRole(Role.ADMIN);

            userRepository.save(admin);

            System.out.println("Utworzono konto admina");
        }
    }
}