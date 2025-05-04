package Projects;

import lombok.Data;

import Enums.Enums.EventType;

import java.time.LocalDateTime;

@Data
public class ScheduleEventDTO {
    private String description;
    private LocalDateTime dateTime;
    private EventType type;
}
