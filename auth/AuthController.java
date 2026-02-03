package com.shop.auth;

import com.shop.config.JwtUtil;
import com.shop.user.User;
import com.shop.user.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final UserRepository userRepo;
  private final JwtUtil jwtUtil;
  private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  public AuthController(UserRepository userRepo, JwtUtil jwtUtil) {
    this.userRepo = userRepo;
    this.jwtUtil = jwtUtil;
  }

  @PostMapping("/login")
  public Object login(@RequestBody LoginRequest req) {

    User user = userRepo.findByUsername(req.username())
        .orElseThrow(() -> new RuntimeException("Invalid credentials"));

    if (!encoder.matches(req.password(), user.getPasswordHash())) {
      throw new RuntimeException("Invalid credentials");
    }

    String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

    return new AuthResponse(token, user.getRole());
  }
}

