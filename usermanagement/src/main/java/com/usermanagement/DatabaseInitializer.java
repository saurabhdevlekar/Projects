package com.usermanagement;



import com.usermanagement.entity.Role;
import com.usermanagement.entity.User;
import com.usermanagement.repository.RoleRepository;
import com.usermanagement.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer {
    @Bean
    CommandLineRunner init(RoleRepository roleRepository, UserService userService, PasswordEncoder passwordEncoder) {
        return args -> {
            // Create roles
            Role adminRole = roleRepository.save(new Role("ROLE_ADMIN"));
            Role managerRole = roleRepository.save(new Role("ROLE_MANAGER"));
            Role userRole = roleRepository.save(new Role("ROLE_USER"));

            // Create users
            User admin = new User();
            admin.setName("Admin User");
            admin.setEmail("admin@example.com");
            admin.setPassword("password");
            admin.getRoles().add(adminRole);
            userService.createUser(admin);

            User manager = new User();
            manager.setName("Manager User");
            manager.setEmail("manager@example.com");
            manager.setPassword("password");
            manager.getRoles().add(managerRole);
            userService.createUser(manager);

            User user = new User();
            user.setName("Regular User");
            user.setEmail("user@example.com");
            user.setPassword("password");
            user.getRoles().add(userRole);
            userService.createUser(user);
        };
    }
}
