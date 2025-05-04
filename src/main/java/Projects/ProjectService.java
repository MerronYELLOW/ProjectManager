package Projects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import PackUsers.User;
import PackUsers.UserRepository;
import Enums.Enums.ProjectStatus;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    public Project createProject(ProjectDTO dto) {
        Project project = new Project();
        project.setProjectName(dto.getName());
        project.setProjectDescription(dto.getDescription());
        project.setDueDate(dto.getDueDate());

        // Konwersja statusu ze Stringa (jeśli nie null)
        if (dto.getProjectStatus() != null) {
            project.setProjectStatus(ProjectStatus.valueOf(dto.getProjectStatus()));
        }

        // Przypisanie użytkowników po ID (jeśli masz w DTO UserDTO z ID)
        if (dto.getAssignedUsers() != null) {
            List<User> users = dto.getAssignedUsers().stream()
                    .map(userDTO -> userRepository.findById(userDTO.getId()).orElseThrow())
                    .collect(Collectors.toList());
            project.setAssignedUsers(users);
        }

        return projectRepository.save(project);
    }
}
