package com.npci.repository;

import com.npci.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    // For loading by role name (e.g., "ROLE_USER", "ROLE_ADMIN")
    Optional<Role> findByName(String name);
}
