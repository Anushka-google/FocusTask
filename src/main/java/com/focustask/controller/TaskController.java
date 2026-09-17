package com.focustask.controller;

import com.focustask.entity.Task;
import com.focustask.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Phase 9: TaskController exposing the 5 REST CRUD endpoints:
 *
 * 1. POST   /api/tasks      -> Create task (201 Created)
 * 2. GET    /api/tasks      -> List all tasks (200 OK)
 * 3. GET    /api/tasks/{id} -> Get single task (200 OK)
 * 4. PUT    /api/tasks/{id} -> Update task (200 OK)
 * 5. DELETE /api/tasks/{id} -> Delete task (204 No Content)
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    // Constructor Injection: Spring injects TaskService
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // 1. Create a task (POST /api/tasks) -> 201 Created
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    // 2. List all tasks (GET /api/tasks) -> 200 OK
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    // 3. Get single task by ID (GET /api/tasks/{id}) -> 200 OK
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    // 4. Update task by ID (PUT /api/tasks/{id}) -> 200 OK
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        return ResponseEntity.ok(taskService.updateTask(id, task));
    }

    // 5. Delete task by ID (DELETE /api/tasks/{id}) -> 204 No Content
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
