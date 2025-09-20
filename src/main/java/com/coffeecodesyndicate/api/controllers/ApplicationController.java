package com.coffeecodesyndicate.api.controllers;

import com.coffeecodesyndicate.api.models.Application;
import com.coffeecodesyndicate.api.models.ApplicationStatus;
import com.coffeecodesyndicate.api.services.ApplicationService;
import com.coffeecodesyndicate.api.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {
    private final ApplicationService apps;
    private final UserService users;

    public ApplicationController(ApplicationService apps, UserService users) {
        this.apps = apps;
        this.users = users;
    }

    // List all applications
    @GetMapping
    public List<Application> all() {
        return apps.findAll();
    }

    // Get one application
    @GetMapping("/{id}")
    public Application one(@PathVariable Integer id) {
        return apps.findById(id);
    }

    // Create application (body already has user set or null)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Application create(@RequestBody Application a) {
        return apps.create(a);
    }

    // Create application for a specific user
    @PostMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Application createForUser(@PathVariable Integer userId, @RequestBody Application a) {
        a.setUser(users.findById(userId));
        return apps.create(a);
    }

    //update application's status, not the whole Application object
    @PutMapping("/{id}/status")
    public Application updateStatus(@PathVariable Integer id, @RequestParam ApplicationStatus status) {
        return apps.update(id, status);
    }

    // Delete application
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        apps.delete(id);
    }
}
