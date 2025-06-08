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
        // Validate and set relationships if provided
        if (task.getProject() != null && task.getProject().getId() != null) {
            task.setProject(projectRepository.findById(task.getProject().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Project not found")));
        }

        if (task.getAssignee() != null && task.getAssignee().getId() != null) {
            task.setAssignee(userRepository.findById(task.getAssignee().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Assignee not found")));
        }

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

    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        task.setName(taskDetails.getName());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());
        task.setImportance(taskDetails.getImportance());
        task.setDueDate(taskDetails.getDueDate());

        // Update relationships if provided
        if (taskDetails.getProject() != null && taskDetails.getProject().getId() != null) {
            task.setProject(projectRepository.findById(taskDetails.getProject().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Project not found")));
        }

        if (taskDetails.getAssignee() != null && taskDetails.getAssignee().getId() != null) {
            task.setAssignee(userRepository.findById(taskDetails.getAssignee().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Assignee not found")));
        }

        if (taskDetails.getSupervisor() != null && taskDetails.getSupervisor().getId() != null) {
            task.setSupervisor(userRepository.findById(taskDetails.getSupervisor().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Supervisor not found")));
        }

        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        taskRepository.delete(task);
    }
}