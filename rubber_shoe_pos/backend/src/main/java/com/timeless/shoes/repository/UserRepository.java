package com.timeless.shoes.repository;

import com.timeless.shoes.users.User;
import java.util.List;

public interface UserRepository {
    User findByUsername(String username);
    List<User> findAll();
}
