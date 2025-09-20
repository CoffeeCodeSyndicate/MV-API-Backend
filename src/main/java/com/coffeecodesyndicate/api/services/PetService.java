package com.coffeecodesyndicate.api.services;

import com.coffeecodesyndicate.api.models.Pet;
import com.coffeecodesyndicate.api.models.User;
import com.coffeecodesyndicate.api.repositories.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    private final PetRepository repo;

    public PetService(PetRepository repo) {
        this.repo = repo;
    }

    public List<Pet> findAll() { return repo.findAll(); }

    public Pet findById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Pet not found"));
    }

    public List<Pet> findAllByOwner(User owner) {
        return repo.findAll();
    }


    public Pet create(Pet p) { p.setId(null); return repo.save(p); }

    public Pet update(Integer id, Pet p) { p.setId(id); return repo.save(p); }

    public void delete(Integer id) { repo.deleteById(id); }
}
