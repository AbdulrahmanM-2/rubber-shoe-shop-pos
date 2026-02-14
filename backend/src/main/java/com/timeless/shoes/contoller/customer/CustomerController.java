package com.timeless.shoes.controller.customer;

import com.timeless.shoes.customers.Customer;
import com.timeless.shoes.customers.CustomerService;
import com.timeless.shoes.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ApiResponse<?> getAllCustomers() {
        var customers = customerService.getAllCustomers();
        return new ApiResponse<>(true, "All customers retrieved", customers);
    }

    @PostMapping
    public ApiResponse<Customer> createCustomer(@RequestBody Customer customer) {
        var created = customerService.createCustomer(customer);
        return new ApiResponse<>(true, "Customer created", created);
    }
}
