package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Cart;
import org.example.coffeeshopwebsite.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUser(User user);
}
