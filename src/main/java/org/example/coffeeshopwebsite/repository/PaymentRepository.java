package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
