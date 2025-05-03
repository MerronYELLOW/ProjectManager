package Projects;

import jakarta.persistence.*;
import lombok.Data;
import enums.Enums;
import PackUsers.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import enums.Enums.ProjectStatus;
import lombok.Getter;

@Data
@Entity
@Table(name = "projects")
public class Project {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectID;

    @Column(nullable = false)
    private String projectName;

    @Column(length = 1000)
    private String projectDescription;

    @Enumerated(EnumType.STRING)
    private Enums.ProjectStatus projectStatus;

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

    public Project(Long projectID, String projectName, String projectDescription, Enums.ProjectStatus projectStatus, Enums.ProjectImportance importance, User projectLead, List<User> assignedUsers, LocalDate dueDate, List<Task> tasks, Schedule schedule, List<Document> documents)
        {
        this.projectID = projectID;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectStatus = projectStatus;
        this.importance = importance;
        this.projectLead = projectLead;
        this.assignedUsers = assignedUsers;
        this.dueDate = dueDate;
        this.tasks = tasks;
        this.schedule = schedule;
        this.documents = documents;
    }

    public Project(Long projectID){
        this.projectID = projectID;
    }

    public Project() {

    }

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
        this.projectStatus = ProjectStatus.COMPLETED;
        this.tasks.forEach(task -> task.setStatus(Enums.TaskStatus.COMPLETED));
    }

    public void updateProjectDetails(ProjectDTO projectDetails) {
        this.projectName = projectDetails.getName();
        this.projectDescription = projectDetails.getDescription();
        this.dueDate = projectDetails.getDueDate();
        this.importance = projectDetails.getImportance();
    }

    public void addProjectDocument(Document document) {
        document.setProject(this);
        document.setUploadedAt(LocalDateTime.now());
        this.documents.add(document);
    }
}