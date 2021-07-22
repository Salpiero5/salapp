package com.salman.salapp.application.service;

import com.salman.salapp.application.repository.CustomerRepository;
import com.salman.salapp.library.entity.Customer;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@Scope("prototype")
public class CustomerService {

    //public int digit = 0;

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomer(Specification<Customer> spec) {
        return customerRepository.findAll(spec);
    }

    public Optional<Customer> getCustomerById(long id) {
        return customerRepository.findById(id);
    }

    public void insertCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public Optional<List<Customer>> getCustomerByFirstNameIgnoreCase(String name) {
        return customerRepository.findAllByFirstNameLikeIgnoreCase(name);
    }

    public Optional<List<Customer>> getCustomersSorted() {
        return customerRepository.findAllByOrderByFirstNameAsc();
    }

    public Optional<List<Customer>> getCustomersByPhoneWithPattern(String phone) {
        return customerRepository.findCustomersByPhoneLikePattern(phone);
    }
}
