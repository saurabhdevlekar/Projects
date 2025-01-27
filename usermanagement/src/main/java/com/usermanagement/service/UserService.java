//package com.usermanagement.service;
//
//import com.usermanagement.entity.Role;
//import com.usermanagement.entity.User;
//import com.usermanagement.repository.RoleRepository;
//import com.usermanagement.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//
//@Service
//public class UserService {
//    private final UserRepository userRepository;
//    private final RoleRepository roleRepository; // Add this to handle roles
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository; // Inject the role repository
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    // Save a new user with hashed password
//    public User saveUser(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return userRepository.save(user);
//    }
//
//    // Assign a role to an existing user
//    public void assignRoleToUser(String email, String roleName) {
//        Optional<User> userOpt = userRepository.findByEmail(email);
//        if (userOpt.isPresent()) {
//            User user = userOpt.get();
//            Optional<Role> roleOpt = roleRepository.findByName(roleName);
//            if (roleOpt.isPresent()) {
//                Role role = roleOpt.get();
//                Set<Role> roles = user.getRoles() != null ? user.getRoles() : new HashSet<>();
//                roles.add(role); // Add the role to the user's roles
//                user.setRoles(roles);
//                userRepository.save(user);
//            } else {
//                throw new RuntimeException("Role not found");
//            }
//        } else {
//            throw new RuntimeException("User not found");
//        }
//    }
//}





package com.usermanagement.service;


import com.usermanagement.entity.User;
import com.usermanagement.repository.UserRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Set<User> getAllUsers() {
        return new HashSet<>(userRepository.findAll());
    }
}
