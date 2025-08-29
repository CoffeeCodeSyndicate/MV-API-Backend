package com.coffeecodesyndicate.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.coffeecodesyndicate.api.models.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
