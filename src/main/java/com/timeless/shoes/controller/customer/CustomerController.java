package com.timeless.shoes.controller.customer;

import com.timeless.shoes.dto.ApiResponse;
import com.timeless.shoes.customers.Customer;
import com.timeless.shoes.customers.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Customer>>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(new ApiResponse<>(true, "Customers retrieved", customers));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Customer>> createCustomer(@RequestBody Customer customer) {
        Customer saved = customerService.createCustomer(customer);
        return ResponseEntity.ok(new ApiResponse<>(true, "Customer created", saved));
    }
}
