package projectmanagement.PackUsers;

import lombok.Getter;
import lombok.Setter;

import projectmanagement.Enums.Enums.Role;

@Getter
@Setter
public class UserBean {
    Role role; // Możliwe wartości: "SUPER_USER", "ADMIN", "EMPLOYEE"
    String name;
    String email;
    String password;
}
