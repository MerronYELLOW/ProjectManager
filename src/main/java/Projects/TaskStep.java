package Projects;

import PackUsers.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "task_steps")
public class TaskStep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean completed = false;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "completed_by_id")
    private User completedBy;

    public void markCompleted(User user) {
        this.completed = true;
        this.completedAt = LocalDateTime.now();
        this.completedBy = user;
    }

    public void updateDescription(String newDescription) {
        this.description = newDescription;
    }
}
