import PackUsers.UserDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ProjectDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDate dueDate;
    private String projectStatus;
    private List<UserDTO> assignedUsers;
}
