import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "documents")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "file_url", nullable = false)
    private String fileUrl;

    @ManyToOne
    @JoinColumn(name = "uploaded_by_id")
    private User uploadedBy;

    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public void downloadDocument() {
        // Implement download logic or tracking
        // This could involve logging download attempts, incrementing download count, etc.
    }
}