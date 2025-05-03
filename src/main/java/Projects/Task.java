<<<<<<<< HEAD:src/main/resources/archetype-resources/src/main/java/Task.java
========
package Projects;

import enums.Enums.TaskStatus;
import enums.Enums.TaskImportance;
>>>>>>>> 5c84025 (Resolving problems with projects):src/main/java/Projects/Task.java
import jakarta.persistence.*;
import lombok.Data;
import src.main.java.enums.Enums.TaskImportance;
import src.main.java.enums.Enums.TaskStatus;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    private TaskImportance importance;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private User supervisor;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<TaskStep> steps;

    public void submitTask(String completionNote) {
        this.status = TaskStatus.UNDER_REVIEW;
        // Could log completion note, set submission timestamp, etc.
    }

    public void addStep(TaskStep step) {
        step.setTask(this);
        if (!this.steps.contains(step)) {
            this.steps.add(step);
        }
    }

    public void approveTask(boolean isApproved, String feedback) {
        this.status = isApproved ? TaskStatus.COMPLETED : TaskStatus.REJECTED;
        // Could log feedback, notify assignee, etc.
    }

    public void updateTaskDetails(TaskDTO taskDetails) {
        this.name = taskDetails.getName();
        this.description = taskDetails.getDescription();
        this.dueDate = taskDetails.getDueDate();
        this.importance = taskDetails.getImportance();
    }
}
