package com.focustask.controller;

import com.focustask.entity.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    /**
     * Phase 4 Milestone: Simple endpoint returning JSON.
     */
    @GetMapping("/test")
    public ResponseEntity<Map<String, Object>> testEndpoint() {
        return ResponseEntity.ok(Map.of(
                "message", "FocusTask REST API is live",
                "status", "ready"
        ));
    }

    /**
     * Phase 5 Milestone: Endpoint returning Task entity serialized directly to JSON.
     */
    @GetMapping("/sample")
    public ResponseEntity<Task> getSampleTask() {
        Task sample = new Task("Learn Spring Boot", "Complete FocusTask roadmap step by step", false);
        sample.setId(1L);
        return ResponseEntity.ok(sample);
    }
}
