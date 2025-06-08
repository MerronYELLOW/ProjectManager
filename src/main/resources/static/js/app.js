// Initialize Lucide icons
lucide.createIcons();

// Application state
let currentUser = { id: 1, name: 'Admin User', email: 'admin@example.com', role: 'ADMIN' };
let projects = [];
let users = [];
let tasks = [];
let teams = [];

// Enhanced section switching with data refresh
function showSection(sectionName) {
    // Hide all sections
    document.querySelectorAll('.section').forEach(section => {
        section.classList.add('hidden');
    });

    // Remove active class from all nav items
    document.querySelectorAll('.nav-item').forEach(item => {
        item.classList.remove('active');
    });

    // Show selected section
    document.getElementById(sectionName + '-section').classList.remove('hidden');

    // Add active class to clicked nav item
    event.target.closest('.nav-item').classList.add('active');

    // Update page title
    const titles = {
        'dashboard': 'Dashboard',
        'projects': 'Projects',
        'tasks': 'Tasks',
        'users': 'Users',
        'teams': 'Teams',
        'calendar': 'Calendar'
    };
    document.getElementById('page-title').textContent = titles[sectionName];

    // Load fresh data for the section - ENHANCED VERSION
    refreshSectionData(sectionName);
}

// New function to refresh data for specific sections
async function refreshSectionData(sectionName) {
    console.log(`Refreshing data for section: ${sectionName}`);

    try {
        switch(sectionName) {
            case 'dashboard':
                // Load all data for dashboard
                await Promise.all([loadProjects(), loadUsers(), loadTasks(), loadTeams()]);
                break;
            case 'projects':
                await loadProjects();
                break;
            case 'users':
                await loadUsers();
                break;
            case 'tasks':
                await loadTasks();
                break;
            case 'teams':
                await loadTeams();
                break;
            case 'calendar':
                // Calendar might need projects and tasks for events
                await Promise.all([loadProjects(), loadTasks()]);
                break;
        }

        console.log(`Data refreshed for section: ${sectionName}`);
    } catch (error) {
        console.error(`Error refreshing data for section ${sectionName}:`, error);
        showNotification(`Error loading ${sectionName} data. Please try again.`, 'error');
    }
}

// Modal functions
function showModal(modalId) {
    document.getElementById(modalId).style.display = 'block';
}

function hideModal(modalId) {
    document.getElementById(modalId).style.display = 'none';
}

// View functions
function viewProject(projectId) {
    const project = projects.find(p => p.id === projectId);
    if (project) {
        alert(`Project: ${project.name}\nDescription: ${project.description || 'No description'}\nStatus: ${project.status}\nImportance: ${project.importance}`);
    }
}

function viewUser(userId) {
    const user = users.find(u => u.id === userId);
    if (user) {
        alert(`User: ${user.name}\nEmail: ${user.email}\nRole: ${user.role}`);
    }
}

function viewTask(taskId) {
    const task = tasks.find(t => t.id === taskId);
    if (task) {
        alert(`Task: ${task.name}\nDescription: ${task.description || 'No description'}\nStatus: ${task.status}\nImportance: ${task.importance}`);
    }
}

// Helper functions
function getStatusClass(status) {
    if (!status) return 'pending';

    const statusMap = {
        'TODO': 'pending',
        'IN_PROGRESS': 'in-progress',
        'UNDER_REVIEW': 'pending',
        'COMPLETED': 'completed',
        'REJECTED': 'cancelled',
        'PENDING': 'pending',
        'CANCELLED': 'cancelled'
    };

    return statusMap[status] || 'pending';
}

function formatStatus(status) {
    if (!status) return 'TODO';
    return status.replace('_', ' ');
}

function logApiError(operation, response, error) {
    console.group(`API Error - ${operation}`);
    if (response) {
        console.log('Status:', response.status);
        console.log('Status Text:', response.statusText);
        console.log('URL:', response.url);
    }
    if (error) {
        console.log('Error:', error);
    }
    console.groupEnd();
}

// Enhanced notification function
function showNotification(message, type) {
    // Create a simple toast notification
    const notification = document.createElement('div');
    notification.style.cssText = `
        position: fixed;
        top: 20px;
        right: 20px;
        padding: 1rem 1.5rem;
        border-radius: 10px;
        color: white;
        font-weight: 500;
        z-index: 10000;
        box-shadow: 0 8px 30px rgba(0, 0, 0, 0.2);
        transform: translateX(400px);
        transition: transform 0.3s ease;
    `;

    if (type === 'error') {
        notification.style.background = 'linear-gradient(135deg, #dc2626, #b91c1c)';
    } else {
        notification.style.background = 'linear-gradient(135deg, #059669, #047857)';
    }

    notification.textContent = message;
    document.body.appendChild(notification);

    // Animate in
    setTimeout(() => {
        notification.style.transform = 'translateX(0)';
    }, 100);

    // Remove after 3 seconds
    setTimeout(() => {
        notification.style.transform = 'translateX(400px)';
        setTimeout(() => {
            document.body.removeChild(notification);
        }, 300);
    }, 3000);
}