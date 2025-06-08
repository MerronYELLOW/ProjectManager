package projectmanagement.PackUsers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint do tworzenia nowego użytkownika przez Super Usera
     */
    // @AuthenticationPrincipal User currentUser
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserBean userBean) {
        User createdUser = userService.createUser(userBean);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    /**
     * Endpoint do pobierania użytkownika po ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * Endpoint do pobierania wszystkich użytkowników
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}