package com.coffeecodesyndicate.api.controllers;

import com.coffeecodesyndicate.api.models.Application;
import com.coffeecodesyndicate.api.services.ApplicationService;
import com.coffeecodesyndicate.api.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApplicationController {
    private final ApplicationService apps;
    private final UserService users;

    public ApplicationController(ApplicationService apps, UserService users) {
        this.apps = apps;
        this.users = users;
    }

    // List all applications
    @GetMapping("/apps")
    public List<Application> all() {
        return apps.findAll();
    }

    // Get one application
    @GetMapping("/apps/{id}")
    public Application one(@PathVariable Integer id) {
        return apps.findById(id);
    }

    // Create application (body already has user set or null)
    @PostMapping("/apps")
    @ResponseStatus(HttpStatus.CREATED)
    public Application create(@RequestBody Application a) {
        return apps.create(a);
    }

    // Create application for a specific user
    @PostMapping("/apps/user/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Application createForUser(@PathVariable Integer userId, @RequestBody Application a) {
        a.setUser(users.findById(userId));
        return apps.create(a);
    }

    // Update application
    @PutMapping("/apps/{id}")
    public Application update(@PathVariable Integer id, @RequestBody Application a) {
        return apps.update(id, a);
    }

    // Delete application
    @DeleteMapping("/apps/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        apps.delete(id);
    }
}
