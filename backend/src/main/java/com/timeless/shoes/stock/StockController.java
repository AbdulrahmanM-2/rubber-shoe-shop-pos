package com.timeless.shoes.customers;

import java.util.Optional;

public interface CustomerRepository {
    Optional<Customer> findById(Long id);
    long count();
}
