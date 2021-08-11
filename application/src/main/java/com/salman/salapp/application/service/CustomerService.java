package com.salman.salapp.application.service;

import com.salman.salapp.application.repository.CustomerRepository;
import com.salman.salapp.application.repository.CustomerSpecification;
import com.salman.salapp.library.entity.Customer;
import com.salman.salapp.library.filter.CustomerFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@Scope("prototype")
public class CustomerService {

    //public int digit = 0;

    private final CustomerRepository customerRepository;
    private final CustomerSpecification customerSpecification;

    public CustomerService(CustomerRepository customerRepository, CustomerSpecification customerSpecification) {
        this.customerRepository = customerRepository;
        this.customerSpecification = customerSpecification;
    }

    public List<Customer> getCustomers(Specification<Customer> spec) {
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

    public List<Customer> getCustomersByFilter(CustomerFilter customerFilter) {
        return customerRepository.findAll(customerSpecification.getCustomers(customerFilter));
    }
}
