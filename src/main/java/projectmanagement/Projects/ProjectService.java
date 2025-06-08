package projectmanagement.Projects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectmanagement.PackUsers.ResourceNotFoundException;
import projectmanagement.PackUsers.User;
import projectmanagement.PackUsers.UserRepository;
import projectmanagement.Enums.Enums.ProjectStatus;
import projectmanagement.Enums.Enums.ProjectImportance;

import java.util.List;

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
        project.setName(p.getName().trim());

        // Set description (can be null or empty)
        if (p.getDescription() != null) {
            project.setDescription(p.getDescription().trim());
        }

        project.setDueDate(p.getDueDate());

        // Set status with default
        if (p.getStatus() != null) {
            project.setStatus(p.getStatus());
        } else {
            project.setStatus(ProjectStatus.PENDING);
        }

        // Set importance with default
        if (p.getImportance() != null) {
            project.setImportance(p.getImportance());
        } else {
            project.setImportance(ProjectImportance.MEDIUM);
        }

        // Set project lead if provided
        if (p.getProjectLead() != null && p.getProjectLead().getId() != null) {
            User projectLead = userRepository.findById(p.getProjectLead().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Project lead not found with id: " + p.getProjectLead().getId()));
            project.setProjectLead(projectLead);
        }

        Project savedProject = projectRepository.save(project);
        System.out.println("Project created successfully: " + savedProject.getName() + " with ID: " + savedProject.getId());
        return savedProject;
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
    }

    public Project updateProject(Long id, Project projectDetails) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        // Update basic fields
        if (projectDetails.getName() != null && !projectDetails.getName().trim().isEmpty()) {
            project.setName(projectDetails.getName().trim());
        }

        if (projectDetails.getDescription() != null) {
            project.setDescription(projectDetails.getDescription().trim());
        }

        if (projectDetails.getDueDate() != null) {
            project.setDueDate(projectDetails.getDueDate());
        }

        if (projectDetails.getStatus() != null) {
            project.setStatus(projectDetails.getStatus());
        }

        if (projectDetails.getImportance() != null) {
            project.setImportance(projectDetails.getImportance());
        }

        // Update project lead if provided
        if (projectDetails.getProjectLead() != null && projectDetails.getProjectLead().getId() != null) {
            User projectLead = userRepository.findById(projectDetails.getProjectLead().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Project lead not found with id: " + projectDetails.getProjectLead().getId()));
            project.setProjectLead(projectLead);
        }

        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
        projectRepository.delete(project);
    }
}