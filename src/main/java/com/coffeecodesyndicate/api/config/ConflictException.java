package com.coffeecodesyndicate.api.config;

/**
 * Custom exception to represent 409 Conflict errors,
 * e.g., when a username or email is already in use.
 */
public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message);
    }

    public ConflictException(String message, Throwable cause) {
        super(message, cause);
    }
}
