package com.anirudhgv.stockease.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anirudhgv.stockease.model.User;
import com.anirudhgv.stockease.model.dto.UserDto;
import com.anirudhgv.stockease.model.response.AuthResponse;
import com.anirudhgv.stockease.service.AuthService;





@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/test")
    public String test() {
        return "Api is working";
    } 
    
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody User user) {
        System.out.println(user);
        AuthResponse authResponse = authService.register(user);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserDto user) {
        System.out.println(user);
        AuthResponse authResponse = authService.login(user);
        return ResponseEntity.ok(authResponse);
    }
    
    
}
