package com.coffeecodesyndicate.api.services;

import com.coffeecodesyndicate.api.models.User;
import com.coffeecodesyndicate.api.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User registerUser(User user) {
        if (userRepository.findUserByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setLoggedIn(false); //user is going to be isLoggedIn, but not isAdmin
        user.setAdmin(false);

        return userRepository.save(user);
    }

    public User registerAdminUser(User user) {
        if (userRepository.findUserByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setLoggedIn(true); //user is going to be isLoggedIn and isAdmin
        user.setAdmin(true);

        return userRepository.save(user);
    }

    public User loginUser(String username, String password) {
        Optional<User> userOptional = userRepository.findUserByUsername(username);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        User user = userOptional.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        user.setLoggedIn(true);
        return userRepository.save(user);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
