Projects manager II, topic number 5.

Projects authors
Maksym Krzemiński and Błażej Majewski
# Projects Management Platform

## Overview
This is a comprehensive project management system designed to streamline team collaboration, task tracking, and project management within organizations.

## System Architecture
- **Backend**: Spring Boot
- **ORM**: Hibernate
- **Database**: MySQL
- **Authentication**: Spring Security
- **API Documentation**: Swagger

## Projects Structure
```
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
│   │   │   │   ├── PackUsers.UserController.java
│   │   │   │   └── ScheduleController.java
│   │   │   │
│   │   │   ├── service/
│   │   │   │   ├── ProjectService.java
│   │   │   │   ├── TaskService.java
│   │   │   │   ├── PackUsers.UserService.java
│   │   │   │   └── ScheduleService.java
│   │   │   │
│   │   │   ├── repository/
│   │   │   │   ├── ProjectRepository.java
│   │   │   │   ├── TaskRepository.java
│   │   │   │   ├── PackUsers.UserRepository.java
│   │   │   │   └── ScheduleRepository.java
│   │   │   │
│   │   │   ├── model/
│   │   │   │   ├── Projects.java
│   │   │   │   ├── Projects.Task.java
│   │   │   │   ├── User.java
│   │   │   │   ├── PackUsers.Team.java
│   │   │   │   └── Projects.Schedule.java
│   │   │   │
│   │   │   └── dto/
│   │   │       ├── Projects.ProjectDTO.java
│   │   │       ├── Projects.TaskDTO.java
│   │   │       └── PackUsers.UserDTO.java
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
```

## Key Features
- Role-based Access Control
- Projects and Projects.Task Management
- PackUsers.Team Collaboration
- Projects.Schedule and Calendar Management
- User Authentication and Authorization

## Setup and Installation
1. Clone the repository
2. Configure MySQL database in `application.properties`
3. Run `mvn clean install`
4. Start the application with `mvn spring-boot:run`

## User Roles
- **Employee**: Basic project participation
- **Projects Creator**: Initiates and manages projects
- **Projects Lead**: Manages project lifecycle
- **Direct Supervisor**: Oversees team projects
- **Super-User**: Administrative rights
- **Platform Administrator**: System management

## API Endpoints
- `/api/projects`: Projects management
- `/api/tasks`: Projects.Task management
- `/api/users`: User management
- `/api/schedule`: Projects scheduling

## Security
- Role-based access control
- HTTPS communication

## Technology Stack
- Java 17
- Spring Boot 3.x
- Hibernate 6.x
- MySQL 8.x
- Swagger OpenAPI
- Spring Security
- Maven

## Contribution Guidelines
1. Fork the repository
2. Create feature branches
3. Write unit tests
4. Follow coding standards
5. Submit pull requests

## License
[Specify your license here]

## Contact
[Your organization contact information]
