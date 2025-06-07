package projectmanagement.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import projectmanagement.Enums.Enums;
import projectmanagement.PackUsers.*;
import projectmanagement.Projects.*;
import projectmanagement.Enums.Enums.Role;
import projectmanagement.Enums.Enums.ProjectStatus;
import projectmanagement.Enums.Enums.TaskStatus;
import projectmanagement.Enums.Enums.ProjectImportance;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void run(String... args) throws Exception {
        // Only initialize if database is empty
        if (userRepository.count() == 0) {
            initializeData();
        }
    }

    private void initializeData() {
        // Create sample users
        User admin = new User();
        admin.setName("Admin User");
        admin.setEmail("admin@example.com");
        admin.setRole(Role.ADMIN);
        userRepository.save(admin);

        User projectLead = new User();
        projectLead.setName("John Doe");
        projectLead.setEmail("john.doe@example.com");
        projectLead.setRole(Role.PROJECT_LEAD);
        userRepository.save(projectLead);

        User developer = new User();
        developer.setName("Jane Smith");
        developer.setEmail("jane.smith@example.com");
        developer.setRole(Role.EMPLOYEE);
        userRepository.save(developer);

        User designer = new User();
        designer.setName("Bob Wilson");
        designer.setEmail("bob.wilson@example.com");
        designer.setRole(Role.EMPLOYEE);
        userRepository.save(designer);

        // Create sample teams
        Team devTeam = new Team();
        devTeam.setName("Development Team");
        devTeam.setSupervisor(projectLead);
        teamRepository.save(devTeam);

        Team designTeam = new Team();
        designTeam.setName("Design Team");
        designTeam.setSupervisor(admin);
        teamRepository.save(designTeam);

        // Update users with teams
        developer.setTeam(devTeam);
        designer.setTeam(designTeam);
        userRepository.save(developer);
        userRepository.save(designer);

        // Create sample projects
        Project webProject = new Project();
        webProject.setName("Company Website Redesign");
        webProject.setDescription("Complete redesign of the company website with modern UI/UX");
        webProject.setStatus(ProjectStatus.IN_PROGRESS);
        webProject.setImportance(ProjectImportance.HIGH);
        webProject.setDueDate(LocalDate.now().plusDays(30));
        webProject.setProjectLead(projectLead);
        projectRepository.save(webProject);

        Project mobileProject = new Project();
        mobileProject.setName("Mobile App Development");
        mobileProject.setDescription("Development of a mobile application for iOS and Android");
        mobileProject.setStatus(ProjectStatus.PENDING);
        mobileProject.setImportance(ProjectImportance.STRATEGIC);
        mobileProject.setDueDate(LocalDate.now().plusDays(60));
        mobileProject.setProjectLead(projectLead);
        projectRepository.save(mobileProject);

        Project marketingProject = new Project();
        marketingProject.setName("Marketing Campaign");
        marketingProject.setDescription("Q2 marketing campaign preparation and execution");
        marketingProject.setStatus(ProjectStatus.COMPLETED);
        marketingProject.setImportance(ProjectImportance.MEDIUM);
        marketingProject.setDueDate(LocalDate.now().minusDays(5));
        projectRepository.save(marketingProject);

        // Create sample tasks
        Task designTask = new Task();
        designTask.setName("Create UI Mockups");
        designTask.setDescription("Design mockups for the main pages of the website");
        designTask.setStatus(TaskStatus.IN_PROGRESS);
        designTask.setImportance(Enums.TaskImportance.HIGH);
        designTask.setDueDate(LocalDate.now().plusDays(7));
        designTask.setProject(webProject);
        designTask.setAssignee(designer);
        designTask.setSupervisor(projectLead);
        taskRepository.save(designTask);

        Task developmentTask = new Task();
        developmentTask.setName("Frontend Implementation");
        developmentTask.setDescription("Implement the frontend based on approved designs");
        developmentTask.setStatus(TaskStatus.TODO);
        developmentTask.setImportance(Enums.TaskImportance.HIGH);
        developmentTask.setDueDate(LocalDate.now().plusDays(14));
        developmentTask.setProject(webProject);
        developmentTask.setAssignee(developer);
        developmentTask.setSupervisor(projectLead);
        taskRepository.save(developmentTask);

        Task testingTask = new Task();
        testingTask.setName("User Testing");
        testingTask.setDescription("Conduct user testing sessions for the new website");
        testingTask.setStatus(TaskStatus.TODO);
        testingTask.setImportance(Enums.TaskImportance.MEDIUM);
        testingTask.setDueDate(LocalDate.now().plusDays(21));
        testingTask.setProject(webProject);
        taskRepository.save(testingTask);

        Task researchTask = new Task();
        researchTask.setName("Market Research");
        researchTask.setDescription("Research competitor mobile apps and features");
        researchTask.setStatus(TaskStatus.COMPLETED);
        researchTask.setImportance(Enums.TaskImportance.MEDIUM);
        researchTask.setDueDate(LocalDate.now().minusDays(2));
        researchTask.setProject(mobileProject);
        researchTask.setAssignee(developer);
        taskRepository.save(researchTask);

        Task documentationTask = new Task();
        documentationTask.setName("Technical Documentation");
        documentationTask.setDescription("Create technical documentation for the project");
        documentationTask.setStatus(TaskStatus.UNDER_REVIEW);
        documentationTask.setImportance(Enums.TaskImportance.LOW);
        documentationTask.setDueDate(LocalDate.now().plusDays(10));
        documentationTask.setProject(webProject);
        documentationTask.setAssignee(developer);
        taskRepository.save(documentationTask);

        System.out.println("Sample data initialized successfully!");
    }
}