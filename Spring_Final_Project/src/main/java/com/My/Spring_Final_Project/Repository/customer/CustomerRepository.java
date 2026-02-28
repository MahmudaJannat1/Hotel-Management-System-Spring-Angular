package com.My.Spring_Final_Project.Repository.customer;

import com.My.Spring_Final_Project.Entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmailAndIsActiveTrue(String email);

    List<Customer> findByIsActiveTrue();

    Long countByIsActiveTrue();

    Long countByIsActiveFalse();
}
