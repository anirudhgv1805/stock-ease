package com.anirudhgv.stockease.data.model.dto;

import com.anirudhgv.stockease.data.model.Role;

import java.time.LocalDateTime;

public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Role role;
    private LocalDateTime createdAt;

    public UserDto(Long id, String username, String password, String email, Role role, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.createdAt = createdAt;
    }

    public UserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserDto(String name, String email, String password) {
        this.email = email;
        this.password = password;
        this.username = name;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
