package com.timeless.shoes.customers;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private final List<Customer> customers = new ArrayList<>();

    public Customer createCustomer(Customer customer) {
        customers.add(customer);
        return customer;
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }
}
