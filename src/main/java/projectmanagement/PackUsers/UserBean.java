package projectmanagement.PackUsers;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import projectmanagement.Enums.Enums.Role;

@Getter
@Setter
@ToString
public class UserBean {
    private Role role;
    private String name;
    private String email;
    private String password;

    // Default constructor
    public UserBean() {}

    // Constructor with parameters
    public UserBean(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}