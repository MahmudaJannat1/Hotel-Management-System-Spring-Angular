package com.My.Spring_Final_Project.Repository.auth;

import com.My.Spring_Final_Project.Entity.auth.Role;
import com.My.Spring_Final_Project.Enum.Auth.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleType name);
    boolean existsByName(RoleType name);
}
