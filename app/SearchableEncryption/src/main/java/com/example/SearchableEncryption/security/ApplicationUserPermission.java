package com.example.SearchableEncryption.security;

public enum ApplicationUserPermission {
    USER_READ("user:read"),
    USER_WRITE("user:write");

    private final String pemission;

    ApplicationUserPermission(String pemission) {
        this.pemission = pemission;
    }

    public String getPemission() {
        return pemission;
    }
}
