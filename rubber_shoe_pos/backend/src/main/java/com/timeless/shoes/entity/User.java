package com.timeless.shoes.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "cashier", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CashierShift> shifts;

    @OneToMany(mappedBy = "cashier", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> orders;

    public enum Role {
        CASHIER,
        MANAGER
    }

    public User() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public List<CashierShift> getShifts() { return shifts; }
    public void setShifts(List<CashierShift> shifts) { this.shifts = shifts; }
    public List<Order> getOrders() { return orders; }
    public void setOrders(List<Order> orders) { this.orders = orders; }
}
