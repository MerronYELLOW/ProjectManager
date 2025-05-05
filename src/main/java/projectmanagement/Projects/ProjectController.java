package projectmanagement.Projects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

//    @GetMapping
//    public ResponseEntity<List<Project>> getAllProjects() {
//
//    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project p) {
        Project createdProject = projectService.createProject(p);
        return ResponseEntity.ok(createdProject);
    }
}
