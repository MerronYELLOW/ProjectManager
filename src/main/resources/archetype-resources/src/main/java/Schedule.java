import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "schedule")
    private Project project;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    private List<ScheduleEvent> events;

    public void addEvent(ScheduleEvent event) {
        event.setSchedule(this);
        this.events.add(event);
    }

    public void removeEvent(ScheduleEvent event) {
        this.events.remove(event);
        event.setSchedule(null);
    }

    public List<ScheduleEvent> getUpcomingEvents() {
        return events.stream()
                .filter(event -> event.getDateTime().isAfter(java.time.LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    public void generateProjectTimeline() {
        // Logic to generate project timeline based on events
    }
}