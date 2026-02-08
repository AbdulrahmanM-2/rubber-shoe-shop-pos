package com.timeless.shoes.repository;

import com.timeless.shoes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find user by username (for login/authentication)
     */
    Optional<User> findByUsername(String username);

    /**
     * Check if a username already exists
     */
    boolean existsByUsername(String username);

    /**
     * Find all users with a specific role
     */
    Optional<User> findAllByRole(User.Role role);
}
