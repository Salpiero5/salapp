package com.salman.salapp.application.repository;

import com.salman.salapp.library.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Optional<List<Customer>> findAllByOrderByFirstNameAsc();

    Optional<List<Customer>> findAllByFirstNameLikeIgnoreCase(String name);

    /**
     * This method can be write with jpa
     * @param phone with pattern
     * @return list of customers with equals to phone string pattern
     */
    @Query("select c from Customer c where c.phone like concat('%',:phone,'%')")
    Optional<List<Customer>> findCustomersByPhoneLikePattern(@Param("phone") String phone);
}
