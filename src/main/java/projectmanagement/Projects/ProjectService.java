package projectmanagement.Projects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectmanagement.PackUsers.User;
import projectmanagement.PackUsers.UserRepository;
import projectmanagement.Enums.Enums.ProjectStatus;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project createProject(Project p) {
        Project project = new Project();
        project.setName(p.getName());
        project.setDescription(p.getDescription());
        project.setDueDate(p.getDueDate());

        // Konwersja statusu ze Stringa (jeśli nie null)
        if (p.getStatus() != null) {
            project.setStatus(p.getStatus());
        }

//        // Przypisanie użytkowników po ID - to można jakoś edytować
//        po ustaleniu jak połączeni są userzy z projektem
//        if (project.getAssignedUsers() != null) {
//            List<User> users = project.getAssignedUsers().stream()
//                    .map(userDTO -> userRepository.findById(userDTO.getId()).orElseThrow())
//                    .collect(Collectors.toList());
//            project.setAssignedUsers(users);
//        }

        return projectRepository.save(project);
    }
}
