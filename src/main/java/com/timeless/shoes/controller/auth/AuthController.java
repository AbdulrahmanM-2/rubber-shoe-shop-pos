package com.timeless.shoes.controller.auth;

import com.timeless.shoes.auth.AuthService;
import com.timeless.shoes.auth.LoginRequest;
import com.timeless.shoes.auth.LoginResponse;
import com.timeless.shoes.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.authenticate(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(new ApiResponse<>(true, "Login successful", response));
    }
}
