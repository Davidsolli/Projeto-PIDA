package com.david.amazonas.domains.users;

public enum UserRole {

    ADMIN("admin"),
    BUYER("buyer"),
    SELLER("seller");

    private String role;

    UserRole(String role) {
        role = role;
    }

    public String getRole() {
        return role;
    }
}
