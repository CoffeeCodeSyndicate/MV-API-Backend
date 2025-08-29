package com.coffeecodesyndicate.api.controllers;

import com.coffeecodesyndicate.api.models.Pet;
import com.coffeecodesyndicate.api.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping
public class UserController {
    //Non-registered users can access pets and see specific pets (by id)
    @Autowired
    private PetRepository petRepository;

    //get all pets
    @GetMapping("/pets")
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    };

    //get pet by id
    @GetMapping("/pets/{id}")
    public Pet getPetById(@PathVariable Integer id) {
        return petRepository.findById(id).orElse(null);
    };

}
