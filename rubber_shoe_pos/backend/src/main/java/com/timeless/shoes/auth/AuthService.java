package com.timeless.shoes.auth;

import com.timeless.shoes.users.User;
import com.timeless.shoes.service.UserService;

public class AuthService {
    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public User authenticate(String username, String password) {
        User user = userService.getByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
