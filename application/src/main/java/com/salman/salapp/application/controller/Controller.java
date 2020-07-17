package com.salman.salapp.application.controller;

import com.salman.salapp.application.service.CustomerService;
import com.salman.salapp.library.entity.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/sal-app")
public class Controller {

    CustomerService customerService;

    public Controller(CustomerService customerService) {
        this.customerService = customerService;
    }

    //@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
    @GetMapping(value = "/customers")
    public List<Customer> getCustomers() {
        return customerService.getCustomer();
    }
}
