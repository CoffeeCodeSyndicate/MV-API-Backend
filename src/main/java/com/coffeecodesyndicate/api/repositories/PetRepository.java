package com.coffeecodesyndicate.api.repositories;

import com.coffeecodesyndicate.api.models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Integer> {}
