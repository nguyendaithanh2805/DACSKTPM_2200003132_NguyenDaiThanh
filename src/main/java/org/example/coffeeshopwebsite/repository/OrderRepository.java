package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
