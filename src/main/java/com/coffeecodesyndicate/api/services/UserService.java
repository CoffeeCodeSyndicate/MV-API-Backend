package com.coffeecodesyndicate.api.services;

import com.coffeecodesyndicate.api.models.User;
import com.coffeecodesyndicate.api.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
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

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
