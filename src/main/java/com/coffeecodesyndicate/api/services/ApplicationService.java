package com.coffeecodesyndicate.api.services;

import com.coffeecodesyndicate.api.models.Application;
import com.coffeecodesyndicate.api.models.ApplicationStatus;
import com.coffeecodesyndicate.api.models.Pet;
import com.coffeecodesyndicate.api.models.User;
import com.coffeecodesyndicate.api.repositories.ApplicationRepository;
import com.coffeecodesyndicate.api.repositories.PetRepository;
import com.coffeecodesyndicate.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final PetRepository petRepository;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository, UserRepository userRepository, PetRepository petRepository) {
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.petRepository = petRepository;
    }

    public List<Application> findAll() {
        return applicationRepository.findAll();
    }

    public Application findById(Integer id) {
        return applicationRepository.findById(id).orElseThrow(() -> new RuntimeException("Application not found"));
    }


    public Application create(Application application) {
        User user = userRepository.findById(application.getUser().getId()).orElseThrow(() -> new RuntimeException("User not found"));
        Pet pet = petRepository.findById(application.getPet().getId()).orElseThrow(() -> new RuntimeException("Pet not found"));

        application.setUser(user);
        application.setPet(pet);
        application.setStatus(ApplicationStatus.PENDING);
        application.setApplicationDate(LocalDate.now());

        return applicationRepository.save(application);
    }

    public Application update(Integer id, ApplicationStatus status) {
        Application application = findById(id);
        application.setStatus(status);
        return applicationRepository.save(application);
    }

    public void delete(Integer id) {
        applicationRepository.deleteById(id);
    }
}
