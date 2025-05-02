import jakarta.persistence.*;
import lombok.Data;
import enums.Enums;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    private Enums.ProjectStatus status;

    @Enumerated(EnumType.STRING)
    private Enums.ProjectImportance importance;

    @ManyToOne
    @JoinColumn(name = "project_lead_id")
    private User projectLead;

    @ManyToMany
    @JoinTable(
            name = "project_users",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> assignedUsers;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> tasks;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Document> documents;

    public Task createTask(TaskDTO taskDetails) {
        Task task = new Task();
        task.setName(taskDetails.getName());
        task.setDescription(taskDetails.getDescription());
        task.setDueDate(taskDetails.getDueDate());
        task.setImportance(taskDetails.getImportance());
        task.setProject(this);
        task.setStatus(Enums.TaskStatus.TODO);
        this.tasks.add(task);
        return task;
    }

    public void assignUsers(List<User> users) {
        users.forEach(user -> {
            if (!this.assignedUsers.contains(user)) {
                this.assignedUsers.add(user);
                user.getProjects().add(this);
            }
        });
    }

    public void closeProject() {
        this.status = ProjectStatus.COMPLETED;
        this.tasks.forEach(task -> task.setStatus(Enums.TaskStatus.COMPLETED));
    }

    public void updateProjectDetails(ProjectDTO projectDetails) {
        this.name = projectDetails.getName();
        this.description = projectDetails.getDescription();
        this.dueDate = projectDetails.getDueDate();
        this.importance = projectDetails.getImportance();
    }

    public void addProjectDocument(Document document) {
        document.setProject(this);
        document.setUploadedAt(LocalDateTime.now());
        this.documents.add(document);
    }
}