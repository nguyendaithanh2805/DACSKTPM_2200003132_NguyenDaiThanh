package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCartRepository extends JpaRepository<ProductCart, Long> {
}
