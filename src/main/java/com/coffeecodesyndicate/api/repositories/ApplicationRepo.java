package com.coffeecodesyndicate.api.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.coffeecodesyndicate.api.models.Application;

public interface ApplicationRepo extends JpaRepository<Application, Integer> {

}
