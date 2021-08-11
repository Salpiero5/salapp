package com.salman.salapp.application.repository;

import com.salman.salapp.library.entity.Customer;
import com.salman.salapp.library.filter.CustomerFilter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Component
public class CustomerSpecification {

    public Specification<Customer> getCustomers(CustomerFilter request) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getPhone() != null && !request.getPhone().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("phone")),
                        "%" + request.getPhone().toLowerCase() + "%"));
            }
            if (request.getEmail() != null && !request.getEmail().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("email")),
                        "%" + request.getEmail().toLowerCase() + "%"));
            }
            if (request.getFirstName() != null && !request.getFirstName().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("firstName"), request.getFirstName()));
            }
            if (request.getLastName() != null && !request.getLastName().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("lastName"), request.getLastName()));
            }
            query.orderBy(criteriaBuilder.asc(root.get("id")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}