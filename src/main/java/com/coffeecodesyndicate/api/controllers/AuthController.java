package com.coffeecodesyndicate.api.controllers;

import com.coffeecodesyndicate.api.dto.AuthDTOS;
import com.coffeecodesyndicate.api.models.User;
import com.coffeecodesyndicate.api.services.UserService;
import com.coffeecodesyndicate.api.config.JService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JService jwt;

    public AuthController(AuthenticationManager authenticationManager,
                          UserService userService,
                          JService jwt) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwt = jwt;
    }

    /**
     * Register a new user.
     * Expects AuthDTOS.RegisterRequest { username, email, password }.
     * Returns AuthDTOS.AuthResponse { token, message }.
     */
    @PostMapping("/register")
    public ResponseEntity<AuthDTOS.AuthResponse> register(@RequestBody AuthDTOS.RegisterRequest req) {
        // build the user model (no raw password inside entity)
        User u = new User();
        u.setUsername(req.username());
        u.setEmail(req.email());

        // service will hash the password and save
        User created = userService.registerUser(u, req.password());

        // issue a JWT for immediate login
        String token = jwt.generateToken(created.getEmail());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new AuthDTOS.AuthResponse(token, "Registered"));
    }

    /**
     * Login with email + password.
     * Expects AuthDTOS.LoginRequest { email, password }.
     * Returns AuthDTOS.AuthResponse { token, message }.
     */
    @PostMapping("/login")
    public ResponseEntity<AuthDTOS.AuthResponse> login(@RequestBody AuthDTOS.LoginRequest req) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.email(), req.password())
            );
            // principal username is the email per SecurityConfig UserDetailsService
            String email = auth.getName();
            String token = jwt.generateToken(email);
            return ResponseEntity.ok(new AuthDTOS.AuthResponse(token, "OK"));
        } catch (BadCredentialsException ex) {
            // consistent 401 when bad creds
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthDTOS.AuthResponse(null, "Invalid credentials"));
        }
    }
}
