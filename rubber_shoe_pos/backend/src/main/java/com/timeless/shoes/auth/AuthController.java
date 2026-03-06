package com.timeless.shoes.auth;  // first line in the file

import com.timeless.shoes.dto.ApiResponse;
import com.timeless.shoes.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.authenticate(request.getUsername(), request.getPassword());
        return new ApiResponse<>(true, "Login successful", response);
    }

}
