package com.coffeecodesyndicate.api.services;

import com.coffeecodesyndicate.api.models.Application;
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

    public Application create(Application a) {
        a.setId(null); // let JPA generate
        return repo.save(a);
    }

    public Application update(Integer id, Application a) {
        if (!repo.existsById(id)) throw new RuntimeException("Application not found");
        a.setId(id);
        return repo.save(a);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
