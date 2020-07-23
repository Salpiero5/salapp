package com.salman.salapp.application.controller;

import com.salman.salapp.application.service.CustomerService;
import com.salman.salapp.library.entity.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class Controller {

    private CustomerService customerService;

    public Controller(CustomerService customerService) {
        this.customerService = customerService;
    }

    //@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
    @GetMapping()
    public ResponseEntity<List<Customer>> getCustomers() {

        List<Customer> customers = customerService.getCustomer();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping(value = "/insert-customer")
    public ResponseEntity insertCustomer(@RequestBody Customer request) {

        customerService.insertCustomer(request);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
