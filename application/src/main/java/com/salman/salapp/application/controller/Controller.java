package com.salman.salapp.application.controller;

import com.salman.salapp.application.service.CustomerService;
import com.salman.salapp.library.entity.Customer;
import com.salman.salapp.library.exceptions.NullIdException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/customers")
public class Controller {

    private CustomerService customerService;

    public Controller(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Value("${welcome.msg}")
    private String welcomeMsg;

    @Value("${some.string}")
    private String randomString;

    /**
     * Method for testing application properties field
     *
     * @return welcomeMsg + randomString
     */
    @GetMapping(value = "/welcome")
    public String welcome() {
        return welcomeMsg + " + random string -> " + randomString;
    }

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
    @GetMapping()
    public ResponseEntity<List<Customer>> getCustomers() {

        List<Customer> customers = customerService.getCustomer();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
    @GetMapping(value = "/id/{id}")
    public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable(value = "id") long id) {

        Optional<Customer> customer = customerService.getCustomerById(id);
        if (!customer.isPresent()) {
            throw new NullIdException("The customer isn't existed with this id {" + id + "}");
        } else {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/insert-customer")
    public ResponseEntity insertCustomer(@RequestBody Customer request) {

        customerService.insertCustomer(request);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(value = "/first-name/{name}")
    public ResponseEntity<Optional<List<Customer>>> getCustomerByFirstNameIgnoreCase(@PathVariable(value = "name") String name) {

        Optional<List<Customer>> customers = customerService.getCustomerByFirstNameIgnoreCase(name);
        if (!customers.isPresent()) {
            throw new NullIdException("The customer isn't existed with this name {" + name + "}");
        } else {
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/sorted-by-name")
    public ResponseEntity<Optional<List<Customer>>> getCustomersSorted() {

        Optional<List<Customer>> customers = customerService.getCustomersSorted();
        if (!customers.isPresent()) {
            throw new NullIdException("The customer isn't existed");
        } else {
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/phone-by-pattern/{phone}")
    public ResponseEntity<Optional<List<Customer>>> getCustomersByPhoneWithPattern(@PathVariable(value = "phone") String phone) {

        Optional<List<Customer>> customers = customerService.getCustomersByPhoneWithPattern(phone);
        if (!customers.isPresent()) {
            throw new NullIdException("The customer isn't existed with this phone {" + phone + "}");
        } else {
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }
    }
}
