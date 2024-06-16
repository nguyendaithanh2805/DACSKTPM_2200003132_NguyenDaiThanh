package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
