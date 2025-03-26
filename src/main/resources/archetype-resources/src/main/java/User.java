import jakarta.persistence.*;
import lombok.Data;
import src.main.java.enums.Enums.Role;

import java.util.List;

@Data
@Entity
@Table(name = "users")
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
    private List<Project> projects;

    public void changePassword(String newPassword) {
        // Password hashing logic
        this.password = passwordEncoder.encode(newPassword);
    }

    public void updateProfile(UserDTO userDetails) {
        this.name = userDetails.getName();
        this.email = userDetails.getEmail();
    }

    public void assignToProject(Project project) {
        if (!this.projects.contains(project)) {
            this.projects.add(project);
            project.getAssignedUsers().add(this);
        }
    }

    public void removeFromProject(Project project) {
        this.projects.remove(project);
        project.getAssignedUsers().remove(this);
    }

    public boolean hasRole(Role role) {
        return this.role == role;
    }
}
