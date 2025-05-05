package projectmanagement.Projects;

import jakarta.persistence.*;
import lombok.Data;
import projectmanagement.Enums.Enums;
import projectmanagement.Enums.Enums.TaskStatus;
import projectmanagement.Enums.Enums.ProjectStatus;
import projectmanagement.PackUsers.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import projectmanagement.Enums.Enums.ProjectImportance;
import lombok.Getter;

@Data
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Lob
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectStatus status = ProjectStatus.PENDING;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectImportance importance = ProjectImportance.MEDIUM;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "project_lead_id")
    private User projectLead;

    @ManyToMany
    @JoinTable(
            name = "user_projects",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Task> tasks = new HashSet<>();

    // --- Getters and Setters ---

}