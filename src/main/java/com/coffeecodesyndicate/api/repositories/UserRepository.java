package com.coffeecodesyndicate.api.repositories;

import com.coffeecodesyndicate.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {}
