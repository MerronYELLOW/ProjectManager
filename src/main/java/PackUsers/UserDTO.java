package PackUsers;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import com.example.projectmanagement.model.Role;

// User DTO
@Data
public class UserDTO {

    private Long id;

    @NotBlank(message = "Email is required")
    @Email(message = "Wrong email address format")
    private String email;

    private String password;

    @NotBlank(message = "name is required")
    private String name;

    private Long teamId;

    @NotNull(message = "Role is required")
    private Role role;
}
