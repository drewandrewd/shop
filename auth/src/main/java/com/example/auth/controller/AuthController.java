package com.example.auth.controller;

import com.example.auth.dto.AuthResponse;
import com.example.auth.dto.UserRequest;
import com.example.auth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    public ResponseEntity<AuthResponse> register(@RequestBody UserRequest request) {
        userService.register(request);
        return ResponseEntity
                .ok(new AuthResponse(request.getUserName() + " registered successfully"));
    }
}
