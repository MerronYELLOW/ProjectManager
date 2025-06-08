// Form handlers for creating new items

// PROJECT FORM HANDLER
function initializeProjectForm() {
    const form = document.getElementById('project-form');
    if (!form) {
        console.error('Project form not found');
        return;
    }

    form.addEventListener('submit', async function(e) {
        e.preventDefault();

        const dueDateValue = document.getElementById('project-due-date').value;

        // Format date to ISO string if provided
        let formattedDueDate = null;
        if (dueDateValue) {
            try {
                formattedDueDate = new Date(dueDateValue).toISOString();
            } catch (error) {
                console.error('Invalid date format:', error);
                showNotification('Invalid date format. Please select a valid date.', 'error');
                return;
            }
        }

        const projectData = {
            name: document.getElementById('project-name').value.trim(),
            description: document.getElementById('project-description').value.trim(),
            status: document.getElementById('project-status').value,
            importance: document.getElementById('project-importance').value,
            dueDate: formattedDueDate
        };

        // Basic validation
        if (!projectData.name) {
            showNotification('Project name is required!', 'error');
            return;
        }

        console.log('Sending project data:', projectData);

        const result = await createProject(projectData);

        if (result.success) {
            projects.push(result.data);
            renderProjects();
            hideModal('project-modal');
            document.getElementById('project-form').reset();
            showNotification('Project created successfully!', 'success');
        } else {
            showNotification(result.error, 'error');
        }
    });
}

// TASK FORM HANDLER
function initializeTaskForm() {
    const form = document.getElementById('task-form');
    if (!form) {
        console.error('Task form not found');
        return;
    }

    form.addEventListener('submit', async function(e) {
        e.preventDefault();

        // Get form elements with error checking
        const nameElement = document.getElementById('task-name');
        const descriptionElement = document.getElementById('task-description');
        const statusElement = document.getElementById('task-status');
        const importanceElement = document.getElementById('task-importance');
        const dueDateElement = document.getElementById('task-due-date');

        // Check if all elements exist
        if (!nameElement || !descriptionElement || !statusElement || !importanceElement || !dueDateElement) {
            console.error('One or more form elements not found');
            showNotification('Form error. Please refresh the page and try again.', 'error');
            return;
        }

        const dueDateValue = dueDateElement.value;

        // Format date to ISO string if provided
        let formattedDueDate = null;
        if (dueDateValue) {
            try {
                formattedDueDate = new Date(dueDateValue).toISOString();
            } catch (error) {
                console.error('Invalid date format:', error);
                showNotification('Invalid date format. Please select a valid date.', 'error');
                return;
            }
        }

        const taskData = {
            name: nameElement.value.trim(),
            description: descriptionElement.value.trim(),
            status: statusElement.value,
            importance: importanceElement.value,
            dueDate: formattedDueDate
        };

        // Basic validation
        if (!taskData.name) {
            showNotification('Task name is required!', 'error');
            return;
        }

        console.log('Sending task data:', taskData);

        const result = await createTask(taskData);

        if (result.success) {
            tasks.push(result.data);
            renderTasks();
            hideModal('task-modal');
            document.getElementById('task-form').reset();
            showNotification('Task created successfully!', 'success');
        } else {
            showNotification(result.error, 'error');
        }
    });
}

// USER FORM HANDLER
function initializeUserForm() {
    const form = document.getElementById('user-form');
    if (!form) {
        console.error('User form not found - this is likely why user creation is not working');
        setTimeout(() => {
            // Try again after a short delay
            const retryForm = document.getElementById('user-form');
            if (retryForm) {
                console.log('User form found on retry, initializing...');
                setupUserFormHandler(retryForm);
            } else {
                console.error('User form still not found after retry');
            }
        }, 1000);
        return;
    }

    setupUserFormHandler(form);
}

function setupUserFormHandler(form) {
    form.addEventListener('submit', async function(e) {
        e.preventDefault();
        console.log('User form submitted');

        const nameField = document.getElementById('user-name');
        const emailField = document.getElementById('user-email');
        const passwordField = document.getElementById('user-password');
        const roleField = document.getElementById('user-role');

        if (!nameField || !emailField || !passwordField || !roleField) {
            console.error('User form fields not found:', {
                name: !!nameField,
                email: !!emailField,
                password: !!passwordField,
                role: !!roleField
            });
            showNotification('Form fields not found. Please refresh the page.', 'error');
            return;
        }

        const userData = {
            name: nameField.value.trim(),
            email: emailField.value.trim(),
            password: passwordField.value.trim(),
            role: roleField.value
        };

        console.log('User data to send:', userData);

        // Basic validation
        if (!userData.name || !userData.email || !userData.password) {
            showNotification('All fields are required!', 'error');
            return;
        }

        // Email validation
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(userData.email)) {
            showNotification('Please enter a valid email address!', 'error');
            return;
        }

        console.log('Sending user data:', userData);

        const result = await createUser(userData);

        if (result.success) {
            users.push(result.data);
            renderUsers();
            hideModal('user-modal');
            form.reset();
            showNotification('User created successfully!', 'success');
        } else {
            showNotification(result.error || 'Error creating user', 'error');
        }
    });
}

// TEAM FORM HANDLER
function initializeTeamForm() {
    const form = document.getElementById('team-form');
    if (!form) {
        console.error('Team form not found');
        return;
    }

    form.addEventListener('submit', async function(e) {
        e.preventDefault();

        const teamData = {
            name: document.getElementById('team-name').value.trim()
        };

        // Basic validation
        if (!teamData.name) {
            showNotification('Team name is required!', 'error');
            return;
        }

        console.log('Sending team data:', teamData);

        const result = await createTeam(teamData);

        if (result.success) {
            teams.push(result.data);
            await loadTeams(); // Reload teams to update the display
            hideModal('team-modal');
            document.getElementById('team-form').reset();
            showNotification('Team created successfully!', 'success');
        } else {
            showNotification(result.error, 'error');
        }
    });
}

// Initialize all form handlers
function initializeForms() {
    console.log('Initializing forms...');

    // Add a small delay to ensure all DOM elements are ready
    setTimeout(() => {
        initializeProjectForm();
        initializeTaskForm();
        initializeUserForm();
        initializeTeamForm();
        console.log('All forms initialized');
    }, 100);
}