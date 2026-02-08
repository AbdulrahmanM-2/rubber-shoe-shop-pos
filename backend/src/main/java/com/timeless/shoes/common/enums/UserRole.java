package com.timeless.shoes.common.enums;

public enum UserRole {
    OWNER,
    MANAGER,
    CASHIER,
    STOREKEEPER
}
if (user.getRole().equals(UserRole.MANAGER.name())) {
    // allow access
}
