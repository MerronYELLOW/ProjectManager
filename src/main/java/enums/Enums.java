package enums;

public class Enums {

    public enum Role {
        EMPLOYEE,
        PROJECT_CREATOR,
        PROJECT_LEAD,
        DIRECT_SUPERVISOR,
        SUPER_USER,
        ADMIN
    }

    public enum ProjectStatus {
        PENDING,
        IN_PROGRESS,
        COMPLETED,
        CANCELLED
    }

    public enum TaskStatus {
        TODO,
        IN_PROGRESS,
        UNDER_REVIEW,
        COMPLETED,
        REJECTED
    }

    public enum TaskImportance {
        LOW,
        MEDIUM,
        HIGH,
        CRITICAL
    }

    public enum ProjectImportance {
        LOW,
        MEDIUM,
        HIGH,
        STRATEGIC
    }

    public enum EventType {
        MEETING,
        MILESTONE,
        DEADLINE,
        REVIEW
    }
}
