package com.coffeecodesyndicate.api.controllers;

import com.coffeecodesyndicate.api.models.Application;
import com.coffeecodesyndicate.api.models.Pet;
import com.coffeecodesyndicate.api.repositories.ApplicationRepository;
import com.coffeecodesyndicate.api.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @GetMapping("/pets")
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @GetMapping("/pets/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable Integer id) {
        Optional<Pet> pet = petRepository.findById(id);
        return pet.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/applications")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('REGISTERED_USER')")
    public Application createApplication(@RequestBody Application application) {
        return applicationRepository.save(application);
    }
}
