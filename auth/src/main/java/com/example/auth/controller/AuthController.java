package com.example.auth.controller;

import com.example.auth.dto.AuthResponse;
import com.example.auth.dto.UserDTO;
import com.example.auth.dto.UserRequest;
import com.example.auth.exception.UserNotFoundException;
import com.example.auth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserRequest request) {
        userService.register(request);
        return ResponseEntity
                .ok(new AuthResponse(request.getUserName() + " registered successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        try {
            userService.getById(id);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
