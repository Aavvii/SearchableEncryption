package com.example.SearchableEncryption.exception;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String reason) {
        super(reason);
    }
    public ResourceNotFoundException(String reason, Throwable t) {
        super(reason, t);
    }
}
