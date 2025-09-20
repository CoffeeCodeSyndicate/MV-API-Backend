package com.coffeecodesyndicate.api.services;

import com.coffeecodesyndicate.api.models.User;
import com.coffeecodesyndicate.api.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public User findById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User create(User u) {
        u.setId(null); // let JPA generate
        return repo.save(u);
    }

    public User update(Integer id, User u) {
        // ensure it exists (optional)
        if (!repo.existsById(id)) throw new RuntimeException("User not found");
        u.setId(id);
        return repo.save(u);
    }

    public Optional<User> findUserByUsername(String username) {
        return repo.findUserByUsername(username);
    }

    public User registerUser(User user) {
        if (repo.findUserByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setIsLoggedIn(true); //user is going to be isLoggedIn, but not isAdmin
        user.setIsAdmin(false);

        return repo.save(user);
    }

    public User registerAdminUser(User user) {
        if (repo.findUserByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setIsLoggedIn(true); //user is going to be isLoggedIn and isAdmin
        user.setIsAdmin(true);

        return repo.save(user);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
