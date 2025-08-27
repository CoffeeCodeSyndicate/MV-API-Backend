package com.coffeecodesyndicate.api.controllers;

import com.coffeecodesyndicate.api.models.Pet;
import com.coffeecodesyndicate.api.repositories.PetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/pets")
@PreAuthorize("hasRole('ADMIN')")

public class AdminPetController {
    @Autowired
    private PetRepo petRepo;

    // create
    @PostMapping
    public Pet createPet(@RequestBody Pet pet) { return petRepo.save(pet); }

    // read
    @GetMapping
    public List<Pet> getAllPets() { return petRepo.findAll(); }

    // update
    @PutMapping
    public Pet updatePet(@PathVariable Integer id, @RequestBody Pet updatedPet) {
        return petRepo.findById(id).map(pet -> {
            pet.setName(updatedPet.getName());
            pet.setAge(updatedPet.getAge());
            pet.setBreed(updatedPet.getBreed());
            pet.setDescription(updatedPet.getDescription());
            return petRepo.save(pet);
        }).orElseThrow();
    }

    // delete
    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable Integer id) {
        petRepo.deleteById(id);
    }
}
