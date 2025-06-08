package projectmanagement.PackUsers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserBean userBean) {
        try {
            System.out.println("POST /api/users - Creating new user");
            System.out.println("Received user data: " + userBean);
            System.out.println("User name: " + userBean.getName());
            System.out.println("User email: " + userBean.getEmail());
            System.out.println("User role: " + userBean.getRole());

            // Validate input
            if (userBean.getName() == null || userBean.getName().trim().isEmpty()) {
                System.err.println("Validation failed: User name is required");
                Map<String, String> error = new HashMap<>();
                error.put("error", "User name is required");
                return ResponseEntity.badRequest().body(error);
            }

            if (userBean.getEmail() == null || userBean.getEmail().trim().isEmpty()) {
                System.err.println("Validation failed: User email is required");
                Map<String, String> error = new HashMap<>();
                error.put("error", "User email is required");
                return ResponseEntity.badRequest().body(error);
            }

            if (userBean.getPassword() == null || userBean.getPassword().trim().isEmpty()) {
                System.err.println("Validation failed: User password is required");
                Map<String, String> error = new HashMap<>();
                error.put("error", "User password is required");
                return ResponseEntity.badRequest().body(error);
            }

            if (userBean.getRole() == null) {
                System.err.println("Validation failed: User role is required");
                Map<String, String> error = new HashMap<>();
                error.put("error", "User role is required");
                return ResponseEntity.badRequest().body(error);
            }

            User createdUser = userService.createUser(userBean);
            System.out.println("User created successfully with ID: " + createdUser.getId());
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);

        } catch (UserAlreadyExistsException e) {
            System.err.println("User already exists: " + e.getMessage());
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);

        } catch (Exception e) {
            System.err.println("Error creating user: " + e.getMessage());
            e.printStackTrace();
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to create user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            System.out.println("GET /api/users/" + id + " - Fetching user by ID");
            User user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (ResourceNotFoundException e) {
            System.err.println("User not found: " + e.getMessage());
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (Exception e) {
            System.err.println("Error fetching user: " + e.getMessage());
            e.printStackTrace();
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to fetch user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        try {
            System.out.println("GET /api/users - Fetching all users");
            List<User> users = userService.getAllUsers();
            System.out.println("Found " + users.size() + " users");
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            System.err.println("Error fetching users: " + e.getMessage());
            e.printStackTrace();
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to fetch users: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // Add OPTIONS endpoint for preflight requests
    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().build();
    }
}