package PackUsers;

import com.example.projectmanagement.model.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserCreationDTO {

    @NotBlank(message = "Email jest wymagany")
    @Email(message = "Niepoprawny format adresu email")
    private String email;

    @NotBlank(message = "Hasło jest wymagane")
    private String password;

    @NotBlank(message = "Imię i nazwisko jest wymagane")
    private String name;

    private Long teamId;

    @NotNull(message = "Rola jest wymagana")
    private Role role;
}