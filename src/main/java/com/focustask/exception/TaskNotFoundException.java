package com.focustask.exception;

/**
 * Custom runtime exception thrown when a requested Task is not found.
 * Extending RuntimeException makes it an unchecked exception.
 */
public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(String message) {
        super(message);
    }

    public TaskNotFoundException(Long id) {
        super("Task not found with id: " + id);
    }
}
