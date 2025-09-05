package com.coffeecodesyndicate.api.repositories;

import com.coffeecodesyndicate.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // find user by email (case insensitive) for login
    Optional<User> findByEmailIgnoreCase(String email);

    // optional: find by username if you also want username-based login
    Optional<User> findByUsernameIgnoreCase(String username);
}
