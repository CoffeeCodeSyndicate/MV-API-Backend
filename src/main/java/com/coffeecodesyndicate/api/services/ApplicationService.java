package com.coffeecodesyndicate.api.services;

import com.coffeecodesyndicate.api.models.Application;
import com.coffeecodesyndicate.api.models.ApplicationStatus;
import com.coffeecodesyndicate.api.repositories.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {
    private final ApplicationRepository repo;

    public ApplicationService(ApplicationRepository repo) {
        this.repo = repo;
    }

    public List<Application> findAll() {
        return repo.findAll();
    }

    public Application findById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Application not found"));
    }

    public Application create(Application application) {
        application.setStatus(ApplicationStatus.PENDING);
        return repo.save(application);
    }

    public Application update(Integer id, ApplicationStatus status) {
        Application application = findById(id);
        application.setStatus(status);
        return repo.save(application);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
