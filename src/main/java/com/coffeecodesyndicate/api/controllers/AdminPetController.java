package com.coffeecodesyndicate.api.controllers;

import com.coffeecodesyndicate.api.models.Pet;
import com.coffeecodesyndicate.api.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/pets")
@PreAuthorize("hasRole('ADMIN')")

public class AdminPetController {
    @Autowired
    private PetRepository petRepository;

    // create
    @PostMapping
    public Pet createPet(@RequestBody Pet pet) { return petRepository.save(pet); }

    // read
    @GetMapping
    public List<Pet> getAllPets() { return petRepository.findAll(); }

    // update
    @PutMapping
    public Pet updatePet(@PathVariable Integer id, @RequestBody Pet updatedPet) {
        return petRepository.findById(id).map(pet -> {
            pet.setName(updatedPet.getName());
            pet.setAge(updatedPet.getAge());
            pet.setBreed(updatedPet.getBreed());
            pet.setDescription(updatedPet.getDescription());
            return petRepository.save(pet);
        }).orElseThrow();
    }

    // delete
    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable Integer id) {
        petRepository.deleteById(id);
    }
}
