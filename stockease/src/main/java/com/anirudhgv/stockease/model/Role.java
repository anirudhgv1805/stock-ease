package com.anirudhgv.stockease.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {

    OWNER("owner"),
    EMPLOYEE("employee");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    @JsonValue
    public String getRole() {
        return roleName;
    }

    @JsonCreator
    public static Role fromString(String role) {
        for (Role r : Role.values()) {
            if (r.roleName.equalsIgnoreCase(role)) {
                return r;
            }
        }
        throw new IllegalArgumentException("Unexpected role is given" + role);
    }
}
