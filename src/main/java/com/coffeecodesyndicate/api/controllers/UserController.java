package com.coffeecodesyndicate.api.controllers;

import com.coffeecodesyndicate.api.dto.AuthDTOS;
import com.coffeecodesyndicate.api.models.Pet;
import com.coffeecodesyndicate.api.models.User;
import com.coffeecodesyndicate.api.repositories.PetRepository;
import com.coffeecodesyndicate.api.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unregistered")
public class UserController {

    private final UserService userService;
    private final PetRepository petRepository;

    public UserController(UserService userService, PetRepository petRepository) {
        this.userService = userService;
        this.petRepository = petRepository;
    }

    // public pet endpoints
    @GetMapping("/pets")
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @GetMapping("/pets/{id}")
    public Pet getPetById(@PathVariable Integer id) {
        return petRepository.findById(id).orElse(null);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    // Register using DTO -> hashes into passwordHash
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User registerUser(@RequestBody AuthDTOS.RegisterRequest req) {
        User u = new User();
        u.setUsername(req.username());
        u.setEmail(req.email());
        return userService.registerUser(u, req.password());
    }
}
