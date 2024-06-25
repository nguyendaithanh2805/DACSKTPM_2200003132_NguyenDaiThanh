package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Cart;
import org.example.coffeeshopwebsite.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);
}
