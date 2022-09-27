package com.gihanz.repositories;

import com.gihanz.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer ,Long> {

    boolean existsByNic(String nic);

    Optional<Customer> findByNic(String nic);
}
