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

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    public boolean emailExists(String email) {
        return userRepository.existsByEmailIgnoreCase(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    /** Register a new user (hash raw password into passwordHash). */
    public User registerUser(User user, String rawPassword) {
        if (userRepository.existsByEmailIgnoreCase(user.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        // Prefer rawPassword param, fallback to transient user.getPassword()
        String toHash = (rawPassword != null && !rawPassword.isBlank())
                ? rawPassword
                : user.getPassword();

        if (toHash == null || toHash.isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }

        user.setPasswordHash(passwordEncoder.encode(toHash));
        user.setPassword(null); // clear transient field

        if (user.getEnabled() == null)   user.setEnabled(true);
        if (user.getLocked() == null)    user.setLocked(false);
        if (user.getIsAdmin() == null)   user.setIsAdmin(false);

        return userRepository.save(user);
    }
}
