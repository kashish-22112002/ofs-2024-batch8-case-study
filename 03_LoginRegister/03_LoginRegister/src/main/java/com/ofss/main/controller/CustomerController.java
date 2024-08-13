package com.ofss.main.controller;

import com.ofss.main.domain.CustomerDetail;
import com.ofss.main.domain.RegistrationDetail;
import com.ofss.main.service.CustomerDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerDetailService customerDetailService;

    // Endpoint for customer registration
    @PostMapping("/register")
    @CrossOrigin
    public ResponseEntity<RegistrationDetail> registerCustomer(@RequestBody RegistrationDetail registrationDetail) {
        RegistrationDetail registeredCustomer = customerDetailService.registerCustomer(registrationDetail);
        return ResponseEntity.ok(registeredCustomer);
    }

    // Endpoint for fetching customer details by ID
    @GetMapping("/{customerId}")
    @CrossOrigin
    public ResponseEntity<CustomerDetail> getCustomerDetailById(@PathVariable int customerId) {
        CustomerDetail customerDetail = customerDetailService.getCustomerDetailById(customerId);
        if (customerDetail != null) {
            return ResponseEntity.ok(customerDetail);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint for fetching customer details by email
    @GetMapping("/email/{email}")
    @CrossOrigin
    public ResponseEntity<CustomerDetail> getCustomerDetailByEmail(@PathVariable String email) {
        CustomerDetail customerDetail = customerDetailService.getCustomerDetailByEmail(email);
        if (customerDetail != null) {
            return ResponseEntity.ok(customerDetail);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
