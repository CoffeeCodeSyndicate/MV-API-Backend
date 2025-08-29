package com.coffeecodesyndicate.api.controllers;

import com.coffeecodesyndicate.api.models.Application;
import com.coffeecodesyndicate.api.models.Pet;
import com.coffeecodesyndicate.api.repositories.ApplicationRepository;
import com.coffeecodesyndicate.api.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@PreAuthorize("hasRole('isRegistered')")
public class RegisteredUserController {
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @GetMapping("/pets")
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    };

    @GetMapping("/pets/{id}")
    public Pet getPetById(@PathVariable Integer id) {
        return petRepository.findById(id).orElse(null);
    };

    //registered users can create an application
    @PostMapping("/applications")
    @ResponseStatus(HttpStatus.CREATED)
    public Application createApplication(@RequestBody Application application) {
        return applicationRepository.save(application);
    }

}