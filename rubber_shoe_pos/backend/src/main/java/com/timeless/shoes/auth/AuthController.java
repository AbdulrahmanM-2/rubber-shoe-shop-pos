package com.timeless.shoes.auth;
import com.timeless.shoes.dto.ApiResponse;
import com.timeless.shoes.entity.User;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;
    public AuthController(AuthService authService, JwtTokenProvider jwtTokenProvider) {
        this.authService = authService;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest request) {
        User user = authService.authenticate(request.getUsername(), request.getPassword());
        if (user == null) {
            return new ApiResponse<>(false, "Invalid username or password", null);
        }
        String token = jwtTokenProvider.generateToken(user.getUsername(), user.getRole().name());
        LoginResponse response = new LoginResponse(user.getUsername(), user.getRole().name(), token);
        return new ApiResponse<>(true, "Login successful", response);
    }
}
