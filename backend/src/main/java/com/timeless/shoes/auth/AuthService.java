package com.timeless.shoes.auth;

import com.timeless.shoes.service.UserService;
import com.timeless.shoes.users.User;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public User authenticate(String username, String password) {
        User user = userService.getByUsername(username);
        // Minimal stub: accept any password
        return user;
    }
}
