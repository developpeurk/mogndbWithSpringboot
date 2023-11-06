package com.lambarki.mongodbdemo.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }

    // Optionally, if you want to include the cause of the exception as well
    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
