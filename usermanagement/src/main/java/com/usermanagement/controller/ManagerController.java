package com.usermanagement.controller;



import com.usermanagement.entity.Task;
import com.usermanagement.entity.User;
import com.usermanagement.repository.TaskRepository;
import com.usermanagement.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manager")
@PreAuthorize("hasRole('MANAGER')")
public class ManagerController {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public ManagerController(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    // View all users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    // Assign a task to a user
    @PostMapping("/assign-task")
    public ResponseEntity<?> assignTask(@RequestParam Long userId, @RequestBody Task task) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        task.setAssignedTo(user);
        taskRepository.save(task);
        return ResponseEntity.ok("Task assigned successfully");
    }
}
