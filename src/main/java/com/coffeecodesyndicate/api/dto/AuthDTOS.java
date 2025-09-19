package com.coffeecodesyndicate.api.dto;

/**
 * Collection of authentication-related DTOs.
 * You can also split these into separate files if you prefer.
 */
public class AuthDTOS {

    // Request body for registration
    public static record RegisterRequest(
            String username,
            String email,
            String password
    ) {}

    // Request body for login
    public static record LoginRequest(
            String email,
            String password
    ) {}

    // Response body after login or register (JWT token and optional message)
    public static record AuthResponse(
            String token,
            String message
    ) {}
}
