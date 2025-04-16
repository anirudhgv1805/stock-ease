package com.anirudhgv.stockease.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anirudhgv.stockease.model.LoginRequest;
import com.anirudhgv.stockease.model.LoginResponse;


@RestController
@RequestMapping("/api")
public class AuthController {

    @PostMapping("/login/")
    public LoginResponse loginUser(@RequestBody LoginRequest loginRequest) {
        return new LoginResponse("thisisatoken");
    }
    
}
