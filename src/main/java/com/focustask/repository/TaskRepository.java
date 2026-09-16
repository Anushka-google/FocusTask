package com.focustask.repository;

import com.focustask.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Phase 7: Repository Layer
 * 
 * TaskRepository extends JpaRepository<Task, Long>.
 * By extending JpaRepository, Spring Data JPA automatically generates
 * the implementation at runtime with complete CRUD capabilities:
 * 
 * - save(Task entity)      -> Inserts or updates a task in MySQL
 * - findAll()              -> Returns List<Task> of all rows
 * - findById(Long id)      -> Returns Optional<Task> (avoids NullPointerException)
 * - deleteById(Long id)    -> Deletes task by primary key
 * - existsById(Long id)    -> Checks if a task exists (returns boolean)
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
