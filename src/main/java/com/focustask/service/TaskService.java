package com.focustask.service;

import com.focustask.entity.Task;
import com.focustask.exception.TaskNotFoundException;
import com.focustask.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Phase 8 & 10: Service Layer
 *
 * Contains business and application logic.
 * Keeps the Controller thin and isolates database operations.
 */
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    // Constructor Injection: Spring supplies the TaskRepository bean automatically
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // 1. Create a new task
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // 2. Get all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // 3. Get a single task by ID
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    // 4. Update an existing task
    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = getTaskById(id);
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setCompleted(updatedTask.isCompleted());
        return taskRepository.save(existingTask);
    }

    // 5. Delete a task by ID
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }
}
