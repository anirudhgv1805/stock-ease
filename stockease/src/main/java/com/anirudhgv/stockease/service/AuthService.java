package com.anirudhgv.stockease.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.anirudhgv.stockease.model.User;
import com.anirudhgv.stockease.model.response.AuthResponse;
import com.anirudhgv.stockease.repository.UserRepository;
import com.anirudhgv.stockease.utils.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthResponse register(User userDto) {
        Optional<User> existingUser = userRepository.findByEmail(userDto.getEmail());
        if (existingUser.isPresent()) {
            return new AuthResponse("fail", "Email already registered", null, null);
        }

        System.out.println("At auth service"+userDto);
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setusername(userDto.getusername());
        user.setRole(userDto.getRole());
        // user.setRole(Role.CLIENT);

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail(),user.getRole());

        return new AuthResponse("success", "User registered successfully", token, user.getRole());
    }

    public AuthResponse login(User userDto) {
        Optional<User> userOptional = userRepository.findByEmail(userDto.getEmail());

        System.out.println("at login"+userDto);
        if (userOptional.isEmpty()) {
            return new AuthResponse("fail", "Invalid credentials", null, null);
        }

        User user = userOptional.get();
        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            return new AuthResponse("fail", "Invalid credentials", null, null);
        }

        String token = jwtUtil.generateToken(user.getEmail(),user.getRole());

        return new AuthResponse("success", "Login successful", token, user.getRole());
    }
}

