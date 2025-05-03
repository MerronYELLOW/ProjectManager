package PackUsers;

import enums.Enums.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserCreationDTO {

    @NotBlank(message = "Email is invalid")
    @Email(message = "Wrong email format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Name is required")
    private String name;

    private Long teamId;

    @NotNull(message = "Role is required")
    private Role role;
}