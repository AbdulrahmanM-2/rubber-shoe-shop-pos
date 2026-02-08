package com.timeless.shoes.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password; // hashed password

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role; // CASHIER or MANAGER

    // One user can have many shifts
    @OneToMany(mappedBy = "cashier", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CashierShift> shifts;

    // One user can have many orders
    @OneToMany(mappedBy = "cashier", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> orders;

    public enum Role {
        CASHIER,
        MANAGER
    }
}
