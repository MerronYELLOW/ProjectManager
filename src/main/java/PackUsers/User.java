package PackUsers;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(mappedBy = "assignedUsers")
    private List<Project> projects = new ArrayList<>();

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public void updateProfile(UserDTO userDetails) {
        this.name = userDetails.getName();
        // inne pola które chcemy zaktualizować
    }

    public void assignToProject(Project project) {
        if (!projects.contains(project)) {
            projects.add(project);
        }
    }

    public void removeFromProject(Project project) {
        projects.remove(project);
    }

    public boolean hasRole(Role role) {
        return this.role == role;
    }
}