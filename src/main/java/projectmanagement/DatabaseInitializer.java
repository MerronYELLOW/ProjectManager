package projectmanagement;

import org.springframework.security.crypto.password.PasswordEncoder;
import projectmanagement.Enums.Enums;
import projectmanagement.Enums.Enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import projectmanagement.PackUsers.User;
import projectmanagement.PackUsers.UserRepository;
import projectmanagement.Projects.Project;
import projectmanagement.Projects.ProjectRepository;

import java.time.LocalDate;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DatabaseInitializer(UserRepository userRepository,
                               ProjectRepository projectRepository,
                               PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Sprawdź czy admin już istnieje
        if (userRepository.findByEmail("admin@example.com").isEmpty()) {
            // Tworzenie użytkownika admin (tylko podczas pierwszego uruchomienia aplikacji)
            User admin = new User();
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
//            admin.setPassword("admin123"); // Hashowanie hasła
            admin.setName("Admin");
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
            System.out.println("Admin account was created");
        }

        if (userRepository.findByEmail("john.doe@example.com").isEmpty()) {
            User john = new User();
            john.setEmail("john.doe@example.com");
            john.setPassword(passwordEncoder.encode("password123"));
            // john.setPassword("password123");
            john.setName("John Doe");
            john.setRole(Role.EMPLOYEE);
            userRepository.save(john);
            System.out.println("User John Doe was created");
        }

        if (projectRepository.findByName("Project Runway").isEmpty()) {
            Project runway = new Project();
            runway.setName("Project Runway");
            runway.setDescription("Very important project tralalo");
            runway.setStatus(Enums.ProjectStatus.PENDING);
            runway.setImportance(Enums.ProjectImportance.HIGH);
            runway.setDueDate(LocalDate.of(2025, 6, 21));
            runway.setProjectLead(userRepository.findByEmail("admin@example.com").get());
            projectRepository.save(runway);
            System.out.println("Project RunWay is officialy created. Letsgooooooo");
        } else {
            System.out.println("Project RunWay already exists");
        }

    }
}