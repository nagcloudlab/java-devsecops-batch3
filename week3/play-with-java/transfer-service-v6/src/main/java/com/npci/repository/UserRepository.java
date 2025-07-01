package com.npci.repository;

import com.npci.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Used by Spring Security's UserDetailsService
    Optional<User> findByUsername(String username);

    // Optional: if you want case-insensitive search
    // @Query("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(:username)")
    // Optional<User> findByUsernameIgnoreCase(@Param("username") String username);


    boolean existsByUsername(String username); // ✅ Add this line
}
