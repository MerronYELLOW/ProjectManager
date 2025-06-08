// Replace your ProjectController.java with this enhanced version:
package projectmanagement.Projects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projectmanagement.PackUsers.ResourceNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        try {
            System.out.println("GET /api/projects - Fetching all projects");
            List<Project> projects = projectService.getAllProjects();
            System.out.println("Found " + projects.size() + " projects");
            return ResponseEntity.ok(projects);
        } catch (Exception e) {
            System.err.println("Error fetching projects: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createProject(@RequestBody Project p) {
        try {
            System.out.println("POST /api/projects - Creating new project");
            System.out.println("Received project data: " + p);
            System.out.println("Project name: " + p.getName());
            System.out.println("Project description: " + p.getDescription());
            System.out.println("Project status: " + p.getStatus());
            System.out.println("Project importance: " + p.getImportance());
            System.out.println("Project due date: " + p.getDueDate());
            System.out.println("Project lead: " + p.getProjectLead());

            // Validate input
            if (p.getName() == null || p.getName().trim().isEmpty()) {
                System.err.println("Validation failed: Project name is required");
                Map<String, String> error = new HashMap<>();
                error.put("error", "Project name is required");
                return ResponseEntity.badRequest().body(error);
            }

            Project createdProject = projectService.createProject(p);
            System.out.println("Project created successfully with ID: " + createdProject.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
        } catch (Exception e) {
            System.err.println("Error creating project: " + e.getMessage());
            e.printStackTrace();

            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to create project: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable Long id) {
        try {
            System.out.println("GET /api/projects/" + id + " - Fetching project by ID");
            Project project = projectService.getProjectById(id);
            return ResponseEntity.ok(project);
        } catch (ResourceNotFoundException e) {
            System.err.println("Project not found: " + e.getMessage());
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (Exception e) {
            System.err.println("Error fetching project: " + e.getMessage());
            e.printStackTrace();

            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to fetch project: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(@PathVariable Long id, @RequestBody Project project) {
        try {
            System.out.println("PUT /api/projects/" + id + " - Updating project");
            Project updatedProject = projectService.updateProject(id, project);
            return ResponseEntity.ok(updatedProject);
        } catch (ResourceNotFoundException e) {
            System.err.println("Project not found for update: " + e.getMessage());
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (Exception e) {
            System.err.println("Error updating project: " + e.getMessage());
            e.printStackTrace();

            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to update project: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id) {
        try {
            System.out.println("DELETE /api/projects/" + id + " - Deleting project");
            projectService.deleteProject(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            System.err.println("Project not found for deletion: " + e.getMessage());
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (Exception e) {
            System.err.println("Error deleting project: " + e.getMessage());
            e.printStackTrace();

            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to delete project: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // Add OPTIONS endpoint for preflight requests
    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().build();
    }
}