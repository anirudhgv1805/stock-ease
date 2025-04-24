package com.anirudhgv.stockease.data.model;

import java.util.Objects;

public class AuthResponse {

    private String status;
    private String message;
    private String accessToken;
    private Role role;

    public AuthResponse() {
    }

    public AuthResponse(String status, String message, String accessToken, Role role) {
        this.status = status;
        this.message = message;
        this.accessToken = accessToken;
        this.role = role;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public AuthResponse status(String status) {
        setStatus(status);
        return this;
    }

    public AuthResponse message(String message) {
        setMessage(message);
        return this;
    }

    public AuthResponse accessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

    public AuthResponse role(Role role) {
        setRole(role);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AuthResponse)) {
            return false;
        }
        AuthResponse authResponse = (AuthResponse) o;
        return Objects.equals(status, authResponse.status) && Objects.equals(message, authResponse.message) && Objects.equals(accessToken, authResponse.accessToken) && Objects.equals(role, authResponse.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message, accessToken, role);
    }

    @Override
    public String toString() {
        return "{" +
                " status='" + getStatus() + "'" +
                ", message='" + getMessage() + "'" +
                ", accessToken='" + getAccessToken() + "'" +
                ", role='" + getRole() + "'" +
                "}";
    }


}
