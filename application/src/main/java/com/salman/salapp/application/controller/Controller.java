package com.salman.salapp.application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customers")
public class Controller {

//    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
    @GetMapping()
    public int getCustomer() {
        return 2;
    }
}
