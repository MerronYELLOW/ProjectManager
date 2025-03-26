import jakarta.persistence.*;
import lombok.Data;
import src.main.java.enums.Enums.EventType;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "schedule_events")
public class ScheduleEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(name = "event_date_time", nullable = false)
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    private EventType type;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public void updateEventDetails(ScheduleEventDTO eventDetails) {
        this.description = eventDetails.getDescription();
        this.dateTime = eventDetails.getDateTime();
        this.type = eventDetails.getType();
    }
}