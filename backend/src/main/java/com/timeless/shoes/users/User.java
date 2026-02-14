package com.timeless.shoes.users;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String phoneNumber;
    private String role;
    private String passwordHash;

    public User() {}

    public String getUsername() { return username; }
    public String getRole() { return role; }
    public String getPasswordHash() { return passwordHash; }
}
