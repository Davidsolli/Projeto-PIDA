package com.david.amazonas.domains.users;

public enum SellerRole {

    ADMIN("admin"),
    SELLER("seller");

    private String role;

    SellerRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
