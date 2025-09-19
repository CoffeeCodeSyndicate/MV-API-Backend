package com.coffeecodesyndicate.api.controllers;

import com.coffeecodesyndicate.api.models.Application;
import com.coffeecodesyndicate.api.models.ApplicationStatus;
import com.coffeecodesyndicate.api.models.User;
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

    @GetMapping("/apps")
    public List<Application> all() {
        return apps.findAll();
    }

    @GetMapping("/apps/{id}")
    public Application one(@PathVariable Integer id) {
        return apps.findById(id);
    }

    @PostMapping("/apps")
    @ResponseStatus(HttpStatus.CREATED)
    public Application create(@RequestBody Application a) {
        return apps.create(a);
    }

    // NOTE: User IDs are Long (UserRepository<Long>)
    @PostMapping("/apps/user/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Application createForUser(@PathVariable Long userId, @RequestBody Application a) {
        User u = users.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
        a.setUser(u);
        return apps.create(a);
    }

    @PutMapping("/apps/{id}/status")
    public Application updateStatus(@PathVariable Integer id, @RequestParam ApplicationStatus status) {
        return apps.update(id, status);
    }

    @DeleteMapping("/apps/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        apps.delete(id);
    }
}
