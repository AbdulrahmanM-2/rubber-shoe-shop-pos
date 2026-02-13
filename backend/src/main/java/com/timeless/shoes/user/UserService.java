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

    /**
     * Create a new user with encoded PIN
     */
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

    /**
     * Retrieve an active user by phone number
     */
    public User getActiveUserByPhone(String phone) {
        return userRepository.findByPhoneNumberAndActiveTrue(phone)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    /**
     * Retrieve user by ID
     */
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    /**
     * Deactivate a user account
     */
    public User deactivateUser(Long id) {
        User user = getUserById(id);
        user.setActive(false);
        return userRepository.save(user);
    }

    /**
     * Update a user's PIN
     */
    public User updatePin(Long id, String newPin) {
        User user = getUserById(id);
        user.setPin(passwordEncoder.encode(newPin));
        return userRepository.save(user);
    }
    }
