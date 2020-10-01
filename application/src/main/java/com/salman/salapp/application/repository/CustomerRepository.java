package com.salman.salapp.application.repository;

import com.salman.salapp.library.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

//    List<Customer> findByIdOrderByName(Long id);
}
