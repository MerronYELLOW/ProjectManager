import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleEventDTO {
    private String description;
    private LocalDateTime dateTime;
    private EventType type;
}
