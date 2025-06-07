package projectmanagement.Projects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectmanagement.PackUsers.ResourceNotFoundException;
import projectmanagement.PackUsers.UserRepository;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task createTask(Task task) {
        // If project ID is provided, set the project
        if (task.getProject() != null && task.getProject().getId() != null) {
            Project project = projectRepository.findById(task.getProject().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
            task.setProject(project);
        }

        // If assignee ID is provided, set the assignee
        if (task.getAssignee() != null && task.getAssignee().getId() != null) {
            task.setAssignee(userRepository.findById(task.getAssignee().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found")));
        }

        // If supervisor ID is provided, set the supervisor
        if (task.getSupervisor() != null && task.getSupervisor().getId() != null) {
            task.setSupervisor(userRepository.findById(task.getSupervisor().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Supervisor not found")));
        }

        return taskRepository.save(task);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }
}