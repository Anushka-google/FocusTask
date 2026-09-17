// FocusTask — Tiny Frontend (Vanilla JS)
// Connects to Spring Boot REST API via fetch() with CORS support

const API_BASE = window.location.port === '8081' 
    ? '/api/tasks' 
    : 'http://localhost:8081/api/tasks';

// DOM Elements
const taskForm = document.getElementById('taskForm');
const taskTitleInput = document.getElementById('taskTitle');
const taskDescInput = document.getElementById('taskDescription');
const submitBtn = document.getElementById('submitBtn');
const btnText = document.getElementById('btnText');
const btnSpinner = document.getElementById('btnSpinner');
const taskList = document.getElementById('taskList');
const taskCountBadge = document.getElementById('taskCountBadge');
const loadingIndicator = document.getElementById('loadingIndicator');
const emptyState = document.getElementById('emptyState');
const alertBanner = document.getElementById('alertBanner');

// In-memory cache of tasks
let tasks = [];

// Initialize on DOM ready
document.addEventListener('DOMContentLoaded', () => {
    loadTasks();
    taskForm.addEventListener('submit', handleCreateTask);
});

// 1. GET /api/tasks — Read all tasks
async function loadTasks() {
    showLoading(true);
    hideAlert();
    try {
        const response = await fetch(API_BASE);
        if (!response.ok) {
            throw new Error(`Failed to load tasks (HTTP ${response.status})`);
        }
        tasks = await response.json();
        renderTasks();
    } catch (err) {
        console.error('Error fetching tasks:', err);
        showAlert('Could not connect to Spring Boot API. Make sure the server is running on port 8081.', 'error');
    } finally {
        showLoading(false);
    }
}

// 2. POST /api/tasks — Create task
async function handleCreateTask(e) {
    e.preventDefault();
    hideAlert();

    const title = taskTitleInput.value.trim();
    const description = taskDescInput.value.trim();

    // Client-side quick check
    if (!title) {
        showAlert('Title is mandatory', 'error');
        taskTitleInput.focus();
        return;
    }

    setSubmitting(true);

    try {
        const response = await fetch(API_BASE, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                title: title,
                description: description,
                completed: false
            })
        });

        const data = await response.json();

        if (response.status === 201) {
            showAlert('Task created successfully!', 'success');
            taskForm.reset();
            loadTasks();
        } else if (response.status === 400 && data.errors) {
            // Display server validation error
            const errorMsg = data.errors.title || data.error || 'Validation error';
            showAlert(`Validation Error: ${errorMsg}`, 'error');
        } else {
            showAlert(data.message || 'Failed to create task', 'error');
        }
    } catch (err) {
        console.error('Error creating task:', err);
        showAlert('Network error while saving task.', 'error');
    } finally {
        setSubmitting(false);
    }
}

// 3. PUT /api/tasks/{id} — Update completion status
async function toggleTaskCompletion(taskId, currentStatus) {
    const task = tasks.find(t => t.id === taskId);
    if (!task) return;

    try {
        const response = await fetch(`${API_BASE}/${taskId}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                title: task.title,
                description: task.description,
                completed: !currentStatus
            })
        });

        if (response.ok) {
            loadTasks();
        } else {
            showAlert(`Failed to update task ${taskId}`, 'error');
        }
    } catch (err) {
        console.error('Error updating task:', err);
        showAlert('Network error while updating task.', 'error');
    }
}

// 4. DELETE /api/tasks/{id} — Delete task
async function deleteTask(taskId) {
    if (!confirm('Are you sure you want to delete this task?')) return;

    try {
        const response = await fetch(`${API_BASE}/${taskId}`, {
            method: 'DELETE'
        });

        if (response.status === 204) {
            showAlert('Task deleted successfully.', 'success');
            loadTasks();
        } else if (response.status === 404) {
            const data = await response.json();
            showAlert(data.message || 'Task not found.', 'error');
            loadTasks();
        } else {
            showAlert('Failed to delete task.', 'error');
        }
    } catch (err) {
        console.error('Error deleting task:', err);
        showAlert('Network error while deleting task.', 'error');
    }
}

// Render tasks to DOM
function renderTasks() {
    taskList.innerHTML = '';
    taskCountBadge.textContent = `${tasks.length} ${tasks.length === 1 ? 'task' : 'tasks'}`;

    if (tasks.length === 0) {
        emptyState.classList.remove('hidden');
        return;
    }

    emptyState.classList.add('hidden');

    tasks.forEach(task => {
        const li = document.createElement('li');
        li.className = `task-item ${task.completed ? 'completed' : ''}`;

        li.innerHTML = `
            <div class="task-left">
                <input type="checkbox" 
                       class="task-checkbox" 
                       ${task.completed ? 'checked' : ''} 
                       onchange="toggleTaskCompletion(${task.id}, ${task.completed})">
                <div class="task-content">
                    <div class="task-title">${escapeHtml(task.title)}</div>
                    ${task.description ? `<div class="task-desc">${escapeHtml(task.description)}</div>` : ''}
                </div>
            </div>
            <button class="btn btn-danger" onclick="deleteTask(${task.id})">Delete</button>
        `;

        taskList.appendChild(li);
    });
}

// Helper: Show/Hide Loading
function showLoading(show) {
    if (show) {
        loadingIndicator.classList.remove('hidden');
        emptyState.classList.add('hidden');
    } else {
        loadingIndicator.classList.add('hidden');
    }
}

// Helper: Submit Button Loading
function setSubmitting(submitting) {
    submitBtn.disabled = submitting;
    if (submitting) {
        btnText.textContent = 'Saving...';
        btnSpinner.classList.remove('hidden');
    } else {
        btnText.textContent = 'Add Task';
        btnSpinner.classList.add('hidden');
    }
}

// Helper: Alerts
function showAlert(message, type) {
    alertBanner.textContent = message;
    alertBanner.className = `alert alert-${type}`;
    alertBanner.classList.remove('hidden');

    if (type === 'success') {
        setTimeout(() => {
            alertBanner.classList.add('hidden');
        }, 3000);
    }
}

function hideAlert() {
    alertBanner.classList.add('hidden');
}

// Helper: XSS Protection
function escapeHtml(text) {
    if (!text) return '';
    const div = document.createElement('div');
    div.textContent = text;
    return div.innerHTML;
}
