package Projects;

import PackUsers.UserDTO;
import enums.Enums;
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

public Enums.ProjectImportance getImportance(Long projectID) {
    // Replace this with actual logic to fetch ProjectImportance based on projectID
    throw new UnsupportedOperationException("Method not implemented");
}
}
