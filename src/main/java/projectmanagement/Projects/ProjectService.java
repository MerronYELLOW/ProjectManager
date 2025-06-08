// Replace your ProjectService.java with this enhanced version:
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
        System.out.println("ProjectService: Fetching all projects from database");
        List<Project> projects = projectRepository.findAll();
        System.out.println("ProjectService: Found " + projects.size() + " projects");
        return projects;
    }

    public Project createProject(Project p) {
        System.out.println("ProjectService: Starting project creation");
        System.out.println("Input project: " + p);

        try {
            Project project = new Project();

            // Set name - this is required
            if (p.getName() == null || p.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("Project name cannot be null or empty");
            }
            project.setName(p.getName().trim());
            System.out.println("Set project name: " + project.getName());

            // Set description (can be null or empty)
            if (p.getDescription() != null) {
                project.setDescription(p.getDescription().trim());
                System.out.println("Set project description: " + project.getDescription());
            }

            // Set due date
            if (p.getDueDate() != null) {
                project.setDueDate(p.getDueDate());
                System.out.println("Set project due date: " + project.getDueDate());
            }

            // Set status with default
            if (p.getStatus() != null) {
                project.setStatus(p.getStatus());
                System.out.println("Set project status: " + project.getStatus());
            } else {
                project.setStatus(ProjectStatus.PENDING);
                System.out.println("Set default project status: PENDING");
            }

            // Set importance with default
            if (p.getImportance() != null) {
                project.setImportance(p.getImportance());
                System.out.println("Set project importance: " + project.getImportance());
            } else {
                project.setImportance(ProjectImportance.MEDIUM);
                System.out.println("Set default project importance: MEDIUM");
            }

            // Set project lead if provided
            if (p.getProjectLead() != null && p.getProjectLead().getId() != null) {
                System.out.println("Looking for project lead with ID: " + p.getProjectLead().getId());
                User projectLead = userRepository.findById(p.getProjectLead().getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Project lead not found with id: " + p.getProjectLead().getId()));
                project.setProjectLead(projectLead);
                System.out.println("Set project lead: " + projectLead.getName());
            } else {
                System.out.println("No project lead specified");
            }

            System.out.println("About to save project: " + project);
            Project savedProject = projectRepository.save(project);
            System.out.println("Project saved successfully with ID: " + savedProject.getId());
            return savedProject;

        } catch (Exception e) {
            System.err.println("Error in ProjectService.createProject: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public Project getProjectById(Long id) {
        System.out.println("ProjectService: Fetching project with ID: " + id);
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
    }

    public Project updateProject(Long id, Project projectDetails) {
        System.out.println("ProjectService: Updating project with ID: " + id);
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
        System.out.println("ProjectService: Deleting project with ID: " + id);
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
        projectRepository.delete(project);
        System.out.println("Project deleted successfully");
    }
}