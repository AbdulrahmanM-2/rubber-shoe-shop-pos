package com.timeless.shoes.customers;

import com.timeless.shoes.customers.Customer;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    public Customer createCustomer(Customer customer) {
        // TODO: implement actual create logic
        return customer;
    }

    public Optional<Customer> getCustomerById(Long id) {
        return Optional.empty();
    }
}
