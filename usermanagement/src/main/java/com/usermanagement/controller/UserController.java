//package com.usermanagement.controller;
//
//
//import com.usermanagement.entity.Task;
//import com.usermanagement.entity.User;
//import com.usermanagement.repository.TaskRepository;
//import com.usermanagement.repository.UserRepository;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/user")
//public class UserController {
//    private final UserRepository userRepository;
//    private final TaskRepository taskRepository;
//
//    public UserController(UserRepository userRepository, TaskRepository taskRepository) {
//        this.userRepository = userRepository;
//        this.taskRepository = taskRepository;
//    }
//
//    // View user's profile
//    @GetMapping("/profile")
//    public ResponseEntity<User> getProfile(Authentication authentication) {
//        String email = authentication.getName();
//        User user = userRepository.findByEmail(email)
//            .orElseThrow(() -> new RuntimeException("User not found"));
//        return ResponseEntity.ok(user);
//    }
//
//    // View tasks assigned to the user
//    @GetMapping("/tasks")
//    public ResponseEntity<List<Task>> getUserTasks(Authentication authentication) {
//        String email = authentication.getName();
//        User user = userRepository.findByEmail(email)
//            .orElseThrow(() -> new RuntimeException("User not found"));
//        List<Task> tasks = taskRepository.findByAssignedToId(user.getId());
//        return ResponseEntity.ok(tasks);
//    }
//}







package com.usermanagement.controller;


import com.usermanagement.entity.User;
import com.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public User getProfile(@RequestParam String email) {
        return userService.getUserByEmail(email).orElse(null);
    }

    @GetMapping("/all")
    public Set<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
