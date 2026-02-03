package com.timeless.shoes.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String phoneNumber;
    private String pin;
}
