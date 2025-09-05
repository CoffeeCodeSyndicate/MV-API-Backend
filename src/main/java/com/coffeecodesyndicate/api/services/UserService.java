package com.coffeecodesyndicate.api.services;

import com.coffeecodesyndicate.api.models.User;
import com.coffeecodesyndicate.api.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll() {
        return repo.findAll();
    }

    public User findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User create(User u) {
        u.setId(null); // let JPA generate
        // encode raw password if present
        if (u.getPasswordHash() != null && !u.getPasswordHash().isBlank()) {
            u.setPasswordHash(passwordEncoder.encode(u.getPasswordHash()));
        }
        return repo.save(u);
    }

    public User update(Long id, User u) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        u.setId(id);
        // encode raw password if it’s changed
        if (u.getPasswordHash() != null && !u.getPasswordHash().isBlank()) {
            u.setPasswordHash(passwordEncoder.encode(u.getPasswordHash()));
        }
        return repo.save(u);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
