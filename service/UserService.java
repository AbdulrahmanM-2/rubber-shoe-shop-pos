package com.timeless.shoes.service;

import com.timeless.shoes.model.User;
import com.timeless.shoes.repository.UserRepository;
import com.timeless.shoes.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(String fullName, String phone, String rawPin, User.Role role) {
        if (userRepository.existsByPhoneNumber(phone)) {
            throw new IllegalArgumentException("Phone number already exists");
        }

        User user = User.builder()
                .fullName(fullName)
                .phoneNumber(phone)
                .pin(passwordEncoder.encode(rawPin))
                .role(role)
                .active(true)
                .build();

        return userRepository.save(user);
    }

    public User getActiveUserByPhone(String phone) {
        return userRepository.findByPhoneNumberAndActiveTrue(phone)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public boolean verifyPin(User user, String rawPin) {
        return passwordEncoder.matches(rawPin, user.getPin());
    }
                                       }
