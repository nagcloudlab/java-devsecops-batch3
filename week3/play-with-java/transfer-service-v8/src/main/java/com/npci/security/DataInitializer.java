package com.npci.security;

import com.npci.model.Role;
import com.npci.model.User;
import com.npci.repository.RoleRepository;
import com.npci.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(
            RoleRepository roleRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            // 1. Create roles if they don't exist
            Role userRole = roleRepository.findByName("ROLE_USER")
                    .orElseGet(() -> roleRepository.save(new Role("ROLE_USER")));

            Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                    .orElseGet(() -> roleRepository.save(new Role("ROLE_ADMIN")));

            // 2. Register user: joe
            if (!userRepository.existsByUsername("joe")) {
                User joe = new User(
                        "joe",
                        passwordEncoder.encode("password"),
                        Set.of(userRole)
                );
                userRepository.save(joe);
                System.out.println("✅ User 'joe' registered");
            }

            // 3. Register user: admin
            if (!userRepository.existsByUsername("admin")) {
                User admin = new User(
                        "admin",
                        passwordEncoder.encode("admin123"),
                        Set.of(adminRole)
                );
                userRepository.save(admin);
                System.out.println("✅ User 'admin' registered");
            }
        };
    }
}
