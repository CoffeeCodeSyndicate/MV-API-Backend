package com.coffeecodesyndicate.api.controllers;

import com.coffeecodesyndicate.api.models.Pet;
import com.coffeecodesyndicate.api.models.User;
import com.coffeecodesyndicate.api.models.Application;
import com.coffeecodesyndicate.api.models.ApplicationStatus;
import com.coffeecodesyndicate.api.repositories.ApplicationRepository;
import com.coffeecodesyndicate.api.repositories.PetRepository;
import com.coffeecodesyndicate.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/pets")
@PreAuthorize("hasRole('isAdmin')")

public class AdminController {
    private final PetRepository PetRepository;
    private final UserRepository UserRepository;
    private final ApplicationRepository ApplicationRepository;

    public AdminController(PetRepository petRepository,
                           UserRepository userRepository,
                           ApplicationRepository applicationRepository) {
        this.PetRepository = petRepository;
        this.UserRepository = userRepository;
        this.ApplicationRepository = applicationRepository;
    }
    // create
    @PostMapping
    public Pet createPet(@RequestBody Pet pet) { return PetRepository.save(pet); }

    // read
    @GetMapping
    public List<Pet> getAllPets() { return PetRepository.findAll(); }

    // update
    @PutMapping
    public Pet updatePet(@PathVariable Integer id, @RequestBody Pet updatedPet) {
        return PetRepository.findById(id).map(pet -> {
            pet.setName(updatedPet.getName());
            pet.setAge(updatedPet.getAge());
            pet.setBreed(updatedPet.getBreed());
            pet.setDescription(updatedPet.getDescription());
            return PetRepository.save(pet);
        }).orElseThrow();
    }

    // delete
    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable Integer id) {
        PetRepository.deleteById(id);
    }

    // create
    @PostMapping("/users")
    public User createUser(@RequestBody User user) { return UserRepository.save(user); }

    // read
    @GetMapping("/users")
    public List<User> getAllUsers() { return UserRepository.findAll(); }

    // update
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {
        return UserRepository.findById(id).map(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setIsAdmin(updatedUser.getIsAdmin());
            user.setIsRegistered(updatedUser.getIsRegistered());
            return UserRepository.save(user);
        }).orElseThrow();
    }

    // delete
    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        UserRepository.deleteById(id);
    }



    // create
    @PostMapping("/applications")
    public Application createApplication(@RequestBody Application application) { return ApplicationRepository.save(application); }

    // read
    @GetMapping("/applications")
    public List<Application> getAllApplications() { return ApplicationRepository.findAll(); }

    // update
    @PutMapping("/applications/{id}")
    public Application updateApplication(@PathVariable Integer id, @RequestBody Application updatedApplication) {
        return ApplicationRepository.findById(id).map(application -> {
            application.setStatus(updatedApplication.getStatus());
            application.setPet(updatedApplication.getPet());
            application.setUser(updatedApplication.getUser());
            application.setFormTitle(updatedApplication.getFormTitle());
            application.setFormBody(updatedApplication.getFormBody());
            application.setApplicationDate(updatedApplication.getApplicationDate());
            return ApplicationRepository.save(application);
        }).orElseThrow();
    }

    // delete
    @DeleteMapping("/applications/{id}")
    public void deleteApplication(@PathVariable Integer id) {
        ApplicationRepository.deleteById(id);
    }


}
