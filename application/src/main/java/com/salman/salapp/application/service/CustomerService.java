package com.salman.salapp.application.service;

import com.salman.salapp.application.repository.CustomerRepository;
import com.salman.salapp.library.entity.Customer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@Scope("prototype")
public class CustomerService {

    //public int digit = 0;

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomer() {
        return (List<Customer>) customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(long id) {
        return customerRepository.findById(id);
    }

    public void insertCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    /*public void printDigit() {
        System.out.println(digit);
    }*/
}
