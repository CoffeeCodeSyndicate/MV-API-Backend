package com.coffeecodesyndicate.api.services;

import com.coffeecodesyndicate.api.models.User;
import com.coffeecodesyndicate.api.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
<<<<<<< HEAD

    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;

=======
    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;

>>>>>>> 86d59849857ec25193912e67624af04cfa311fde
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

<<<<<<< HEAD
    public void delete(Long id) {
=======
    public Optional<User> findUserByUsername(String username) {
        return repo.findUserByUsername(username);
    }

    public User registerUser(User user) {
        if (repo.findUserByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setIsRegistered(true); //user is going to be isRegistered, but not isAdmin
        user.setIsAdmin(false);

        return repo.save(user);
    }

    public User registerAdminUser(User user) {
        if (repo.findUserByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setIsRegistered(true); //user is going to be isRegistered and isAdmin
        user.setIsAdmin(true);

        return repo.save(user);
    }

    public void delete(Integer id) {
>>>>>>> 86d59849857ec25193912e67624af04cfa311fde
        repo.deleteById(id);
    }
}
