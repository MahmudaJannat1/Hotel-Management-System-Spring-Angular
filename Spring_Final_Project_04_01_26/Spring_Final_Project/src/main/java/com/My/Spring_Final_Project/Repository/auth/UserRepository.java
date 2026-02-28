package com.My.Spring_Final_Project.Repository.auth;

import com.My.Spring_Final_Project.Entity.auth.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUserName(String userName);

    Optional<User> findByEmail(String email);

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);

    boolean existsByUserNameIgnoreCase(String userName);

    boolean existsByEmailIgnoreCase(String email);

    long countByEnabledTrue();


    Page<User> findByUserNameContainingIgnoreCaseOrUserFirstNameContainingIgnoreCaseOrUserLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String userName,
            String firstName,
            String lastName,
            String email,
            Pageable pageable
    );
}
