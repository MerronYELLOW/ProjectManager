// Render functions - Updated to handle empty states
function renderProjects() {
    const tbody = document.getElementById('projects-tbody');
    tbody.innerHTML = '';

    if (projects.length === 0) {
        tbody.innerHTML = '<tr><td colspan="6" style="text-align: center; color: #6b7280; padding: 2rem;">No projects found. Create your first project!</td></tr>';
    } else {
        projects.forEach(project => {
            const row = document.createElement('tr');
            const statusClass = project.status ? project.status.toLowerCase().replace('_', '-') : 'pending';
            const importanceClass = project.importance ? project.importance.toLowerCase() : 'low';

            // Format date properly
            let formattedDate = 'No deadline';
            if (project.dueDate) {
                try {
                    const date = new Date(project.dueDate);
                    formattedDate = date.toLocaleDateString();
                } catch (e) {
                    formattedDate = project.dueDate;
                }
            }

            row.innerHTML = `
                <td><strong>${project.name || 'Unnamed Project'}</strong></td>
                <td><span class="status-badge status-${statusClass}">${project.status || 'PENDING'}</span></td>
                <td><span class="status-badge importance-${importanceClass}">${project.importance || 'LOW'}</span></td>
                <td>${formattedDate}</td>
                <td>${project.projectLead ? project.projectLead.name : 'Unassigned'}</td>
                <td>
                    <button class="btn btn-outline" onclick="viewProject(${project.id})" style="margin-right: 0.5rem;">View</button>
                </td>
            `;
            tbody.appendChild(row);
        });
    }

    // Update dashboard stats
    document.getElementById('total-projects').textContent = projects.length;
}

function renderUsers() {
    const tbody = document.getElementById('users-tbody');
    tbody.innerHTML = '';

    if (users.length === 0) {
        tbody.innerHTML = '<tr><td colspan="5" style="text-align: center; color: #6b7280; padding: 2rem;">No users found.</td></tr>';
    } else {
        users.forEach(user => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td><strong>${user.name || 'Unnamed User'}</strong></td>
                <td>${user.email || 'No email'}</td>
                <td><span class="status-badge importance-medium">${user.role || 'EMPLOYEE'}</span></td>
                <td>${user.team ? user.team.name : 'No team assigned'}</td>
                <td>
                    <button class="btn btn-outline" onclick="viewUser(${user.id})">View</button>
                </td>
            `;
            tbody.appendChild(row);
        });
    }

    // Update dashboard stats
    document.getElementById('team-members').textContent = users.length;
}

function renderTasks() {
    const tbody = document.getElementById('tasks-tbody');
    tbody.innerHTML = '';

    if (tasks.length === 0) {
        tbody.innerHTML = '<tr><td colspan="7" style="text-align: center; color: #6b7280; padding: 2rem;">No tasks found. Create your first task!</td></tr>';
    } else {
        tasks.forEach(task => {
            const row = document.createElement('tr');
            const statusClass = getStatusClass(task.status);
            const importanceClass = task.importance ? task.importance.toLowerCase() : 'low';

            // Format date properly
            let formattedDate = 'No deadline';
            if (task.dueDate) {
                try {
                    const date = new Date(task.dueDate);
                    formattedDate = date.toLocaleDateString();
                } catch (e) {
                    formattedDate = task.dueDate;
                }
            }

            row.innerHTML = `
                <td><strong>${task.name || 'Unnamed Task'}</strong></td>
                <td>${task.project ? task.project.name : 'No project'}</td>
                <td><span class="status-badge status-${statusClass}">${formatStatus(task.status)}</span></td>
                <td><span class="status-badge importance-${importanceClass}">${task.importance || 'LOW'}</span></td>
                <td>${task.assignee ? task.assignee.name : 'Unassigned'}</td>
                <td>${formattedDate}</td>
                <td>
                    <button class="btn btn-outline" onclick="viewTask(${task.id})">View</button>
                </td>
            `;
            tbody.appendChild(row);
        });
    }

    // Update dashboard stats
    const activeTasks = tasks.filter(task =>
        task.status !== 'COMPLETED' && task.status !== 'REJECTED'
    ).length;
    document.getElementById('active-tasks').textContent = activeTasks;

    // Calculate completion rate
    const completedTasks = tasks.filter(task => task.status === 'COMPLETED').length;
    const completionRate = tasks.length > 0 ? Math.round((completedTasks / tasks.length) * 100) : 0;
    document.getElementById('completion-rate').textContent = completionRate + '%';
}

function renderTeams() {
    console.log('Teams loaded:', teams.length);
    // Teams section could be enhanced here if needed
    // The current implementation shows static team cards in the HTML
    // This function can be expanded to dynamically render team cards
}

// Function to update dashboard statistics
function updateDashboardStats() {
    // Update project count
    document.getElementById('total-projects').textContent = projects.length;

    // Update active tasks count
    const activeTasks = tasks.filter(task =>
        task.status !== 'COMPLETED' && task.status !== 'REJECTED'
    ).length;
    document.getElementById('active-tasks').textContent = activeTasks;

    // Update team members count
    document.getElementById('team-members').textContent = users.length;

    // Calculate and update completion rate
    const completedTasks = tasks.filter(task => task.status === 'COMPLETED').length;
    const completionRate = tasks.length > 0 ? Math.round((completedTasks / tasks.length) * 100) : 0;
    document.getElementById('completion-rate').textContent = completionRate + '%';

    console.log('Dashboard stats updated');
}