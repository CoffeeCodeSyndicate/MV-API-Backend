package com.coffeecodesyndicate.api.repositories;

import com.coffeecodesyndicate.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCase(String email);
    Optional<User> findByUsernameIgnoreCase(String username);

    // --- Compatibility for teammate's controller (Integer IDs) ---
    default Optional<User> findById(Integer id) {
        return findById(id == null ? null : id.longValue());
    }
    default void deleteById(Integer id) {
        if (id != null) deleteById(id.longValue());
    }
}
