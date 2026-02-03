package com.timeless.shoes.controller;

import com.timeless.shoes.dto.LoginRequest;
import com.timeless.shoes.model.User;
import com.timeless.shoes.service.UserService;
import com.timeless.shoes.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ApiResponse<User> login(@RequestBody LoginRequest request) {

        User user = userService.getActiveUserByPhone(request.getPhoneNumber());

        if (!userService.verifyPin(user, request.getPin())) {
            throw new IllegalArgumentException("Invalid PIN");
        }

        return ApiResponse.success("Login successful", user);
    }
  }
