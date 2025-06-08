// API functions - Updated to handle backend properly
async function loadProjects() {
    try {
        const response = await fetch('/api/projects', {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });

        if (response.ok) {
            projects = await response.json();
            console.log('Projects loaded:', projects);
            renderProjects();
        } else {
            console.error('Failed to load projects:', response.status, response.statusText);
            projects = [];
            renderProjects();
        }
    } catch (error) {
        console.error('Error loading projects:', error);
        projects = [];
        renderProjects();
    }
}

async function loadUsers() {
    try {
        const response = await fetch('/api/users', {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });

        if (response.ok) {
            users = await response.json();
            console.log('Users loaded:', users);
            renderUsers();
        } else {
            console.error('Failed to load users:', response.status, response.statusText);
            users = [];
            renderUsers();
        }
    } catch (error) {
        console.error('Error loading users:', error);
        users = [];
        renderUsers();
    }
}

async function loadTasks() {
    try {
        const response = await fetch('/api/tasks', {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });

        if (response.ok) {
            tasks = await response.json();
            console.log('Tasks loaded:', tasks);
            renderTasks();
        } else {
            console.error('Failed to load tasks:', response.status, response.statusText);
            tasks = [];
            renderTasks();
        }
    } catch (error) {
        console.error('Error loading tasks:', error);
        tasks = [];
        renderTasks();
    }
}

async function loadTeams() {
    try {
        const response = await fetch('/api/teams', {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });

        if (response.ok) {
            teams = await response.json();
            console.log('Teams loaded:', teams);
            renderTeams();
        } else {
            console.error('Failed to load teams:', response.status, response.statusText);
            teams = [];
            renderTeams();
        }
    } catch (error) {
        console.error('Error loading teams:', error);
        teams = [];
        renderTeams();
    }
}

// API POST functions for creating new items
async function createProject(projectData) {
    try {
        const response = await fetch('/api/projects', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify(projectData)
        });

        if (response.ok) {
            const newProject = await response.json();
            console.log('Project created successfully:', newProject);
            return { success: true, data: newProject };
        } else {
            let errorMessage = 'Error creating project. Please try again.';
            try {
                const errorData = await response.json();
                if (errorData.message) {
                    errorMessage = errorData.message;
                } else if (errorData.error) {
                    errorMessage = errorData.error;
                }
            } catch (e) {
                const errorText = await response.text();
                if (errorText) {
                    errorMessage = errorText;
                }
            }
            console.error('Server error:', response.status, errorMessage);
            return { success: false, error: errorMessage };
        }
    } catch (error) {
        console.error('Network error creating project:', error);
        return { success: false, error: 'Network error. Please check your connection and try again.' };
    }
}

async function createTask(taskData) {
    try {
        const response = await fetch('/api/tasks', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify(taskData)
        });

        if (response.ok) {
            const newTask = await response.json();
            console.log('Task created successfully:', newTask);
            return { success: true, data: newTask };
        } else {
            let errorMessage = 'Error creating task. Please try again.';
            try {
                const errorData = await response.json();
                if (errorData.message) {
                    errorMessage = errorData.message;
                } else if (errorData.error) {
                    errorMessage = errorData.error;
                }
            } catch (e) {
                try {
                    const errorText = await response.text();
                    if (errorText) {
                        errorMessage = errorText;
                    }
                } catch (textError) {
                    console.error('Could not read error response:', textError);
                }
            }
            console.error('Server error creating task:', response.status, errorMessage);
            return { success: false, error: errorMessage };
        }
    } catch (error) {
        console.error('Network error creating task:', error);
        return { success: false, error: 'Network error. Please check your connection and try again.' };
    }
}

async function createUser(userData) {
    try {
        const response = await fetch('/api/users', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify(userData)
        });

        if (response.ok) {
            const newUser = await response.json();
            console.log('User created successfully:', newUser);
            return { success: true, data: newUser };
        } else {
            const errorText = await response.text();
            console.error('Server error:', response.status, errorText);
            return { success: false, error: 'Error creating user. Please try again.' };
        }
    } catch (error) {
        console.error('Error creating user:', error);
        return { success: false, error: 'Network error. Please check your connection.' };
    }
}

async function createTeam(teamData) {
    try {
        const response = await fetch('/api/teams', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify(teamData)
        });

        if (response.ok) {
            const newTeam = await response.json();
            console.log('Team created successfully:', newTeam);
            return { success: true, data: newTeam };
        } else {
            const errorText = await response.text();
            console.error('Server error:', response.status, errorText);
            return { success: false, error: 'Error creating team. Please try again.' };
        }
    } catch (error) {
        console.error('Error creating team:', error);
        return { success: false, error: 'Network error. Please check your connection.' };
    }
}