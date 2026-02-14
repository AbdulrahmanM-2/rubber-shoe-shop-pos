package com.timeless.shoes.service;

import com.timeless.shoes.users.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User getByUsername(String username) {
        // Minimal stub
        User user = new User();
        user.setUsername(username);
        user.setPassword("password");
        user.setRole("USER");
        user.setActive(true);
        return user;
    }

    public boolean existsByPhoneNumberAndActiveTrue(String phoneNumber) {
        return true; // stub
    }
}
