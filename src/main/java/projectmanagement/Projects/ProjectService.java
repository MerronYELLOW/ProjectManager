package projectmanagement.Projects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectmanagement.PackUsers.ResourceNotFoundException;
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

        if (p.getImportance() != null) {
            project.setImportance(p.getImportance());
        }

        return projectRepository.save(project);
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
    }
}