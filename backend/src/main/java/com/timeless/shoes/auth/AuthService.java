package com.timeless.shoes.auth;

import com.timeless.shoes.service.UserService;
import com.timeless.shoes.users.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;

    private final String SECRET_KEY = "SuperSecureSecretKey2026";

    public String login(LoginRequest request) {
        User user = userService.getByUsername(request.getUsername());
        if (user == null) throw new RuntimeException("User not found");

        // TODO: check password hash

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
