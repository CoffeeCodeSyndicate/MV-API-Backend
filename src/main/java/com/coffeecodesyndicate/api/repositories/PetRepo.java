package com.coffeecodesyndicate.api.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.coffeecodesyndicate.api.models.Pet;

public interface PetRepo extends JpaRepository<Pet, Integer> {

}