Project manager II, topic number 5.

Project authors Maksym Krzemiński and Błażej Majewski

Project Management Platform
Overview
This is a comprehensive project management system designed to streamline team collaboration, task tracking, and project management within organizations.

System Architecture
Backend: Spring Boot
ORM: Hibernate
Database: MySQL
Authentication: Spring Security
API Documentation: Swagger
Project Structure
project-management-platform/
│
├── src/
│   ├── main/
│   │   ├── java/com/organization/projectmanager/
│   │   │   ├── config/
│   │   │   │   ├── SecurityConfig.java
│   │   │   │   ├── DatabaseConfig.java
│   │   │   │   └── SwaggerConfig.java
│   │   │   │
│   │   │   ├── controller/
│   │   │   │   ├── ProjectController.java
│   │   │   │   ├── TaskController.java
│   │   │   │   ├── UserController.java
│   │   │   │   └── ScheduleController.java
│   │   │   │
│   │   │   ├── service/
│   │   │   │   ├── ProjectService.java
│   │   │   │   ├── TaskService.java
│   │   │   │   ├── UserService.java
│   │   │   │   └── ScheduleService.java
│   │   │   │
│   │   │   ├── repository/
│   │   │   │   ├── ProjectRepository.java
│   │   │   │   ├── TaskRepository.java
│   │   │   │   ├── UserRepository.java
│   │   │   │   └── ScheduleRepository.java
│   │   │   │
│   │   │   ├── model/
│   │   │   │   ├── Project.java
│   │   │   │   ├── Task.java
│   │   │   │   ├── User.java
│   │   │   │   ├── Team.java
│   │   │   │   └── Schedule.java
│   │   │   │
│   │   │   └── dto/
│   │   │       ├── ProjectDTO.java
│   │   │       ├── TaskDTO.java
│   │   │       └── UserDTO.java
│   │   │
│   │   └── resources/
│   │       ├── application.properties
│   │       └── database-schema.sql
│   │
│   └── test/
│       └── java/com/organization/projectmanager/
│           ├── service/
│           └── controller/
│
├── pom.xml
└── README.md
Key Features
Role-based Access Control
Project and Task Management
Team Collaboration
Schedule and Calendar Management
User Authentication and Authorization
Setup and Installation
Clone the repository
Configure MySQL database in application.properties
Run mvn clean install
Start the application with mvn spring-boot:run
User Roles
Employee: Basic project participation
Project Creator: Initiates and manages projects
Project Lead: Manages project lifecycle
Direct Supervisor: Oversees team projects
Super-User: Administrative rights
Platform Administrator: System management
API Endpoints
/api/projects: Project management
/api/tasks: Task management
/api/users: User management
/api/schedule: Project scheduling
Security
Role-based access control
HTTPS communication
Technology Stack
Java 17
Spring Boot 3.x
Hibernate 6.x
MySQL 8.x
Swagger OpenAPI
Spring Security
Maven
Contribution Guidelines
Fork the repository
Create feature branches
Write unit tests
Follow coding standards
Submit pull requests
