import PackUsers.UserDTO;
import lombok.Data;

import java.time.LocalDate;
@Data
public class TaskDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDate dueDate;
    private String taskStatus;
    private UserDTO assignee;
}
