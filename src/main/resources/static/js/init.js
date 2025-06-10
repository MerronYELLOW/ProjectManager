// Initialize the application - ENHANCED VERSION
document.addEventListener('DOMContentLoaded', function() {
    console.log('Application starting...');

    // Enhanced initialization function
    async function initializeApplication() {
        try {
            console.log('Loading initial data...');

            // Load all data in parallel for better performance
            const loadingPromises = [
                loadProjects(),
                loadUsers(),
                loadTasks(),
                loadTeams()
            ];

            // Wait for all data to load
            await Promise.all(loadingPromises);

            console.log('All data loaded successfully');

            // Update dashboard stats after data is loaded
            updateDashboardStats();

        } catch (error) {
            console.error('Error during application initialization:', error);
            showNotification('Error loading application data. Please refresh the page.', 'error');
        }
    }

    // Add this function to global scope for manual refresh
    window.refreshAllData = async function() {
        console.log('Manual refresh triggered...');
        await initializeApplication();
        showNotification('Data refreshed successfully!', 'success');
    };

    // Load modals and then initialize forms
    async function loadModalsAndInitialize() {
        try {
            const response = await fetch('modals.html');
            if (response.ok) {
                const html = await response.text();
                document.getElementById('modals-container').innerHTML = html;
                console.log('Modals loaded successfully');

                // Initialize form handlers AFTER modals are loaded
                initializeForms();
            } else {
                console.error('Failed to load modals.html');
                showNotification('Error loading modals. Some features may not work.', 'error');
            }
        } catch (error) {
            console.error('Error loading modals:', error);
            showNotification('Error loading modals. Some features may not work.', 'error');
        }
    }

    // Load modals first, then initialize everything else
    loadModalsAndInitialize();

    // Start the initialization process immediately
    initializeApplication();

    // Close modals when clicking outside
    window.addEventListener('click', function(event) {
        if (event.target.classList.contains('modal')) {
            event.target.style.display = 'none';
        }
    });

    // Add keyboard shortcut for manual refresh (Ctrl+U)
    document.addEventListener('keydown', function(event) {
        if (event.ctrlKey && event.key === 'u') {
            event.preventDefault();
            window.refreshAllData();
        }
    });

    // Section navigation handler
    document.querySelectorAll('.nav-item').forEach(item => {
        item.addEventListener('click', function(event) {
            // Get the section name from the nav item
            const navText = event.currentTarget.textContent.trim().toLowerCase();
            const sectionMap = {
                'dashboard': 'dashboard',
                'projects': 'projects',
                'tasks': 'tasks',
                'users': 'users',
                'teams': 'teams',
                'calendar': 'calendar'
            };

            const sectionName = sectionMap[navText];
            if (sectionName) {
                showSection(sectionName);
            }
        });
    });

    console.log('Application initialized with enhanced data loading');
});

// Additional initialization for any late-loading elements
window.addEventListener('load', function() {
    // Re-initialize Lucide icons in case any were added dynamically
    lucide.createIcons();

    console.log('Application fully loaded');
});