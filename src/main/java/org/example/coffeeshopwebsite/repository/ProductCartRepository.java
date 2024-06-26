package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCartRepository extends JpaRepository<ProductCart, Long> {
    /* Test query in database
        SELECT * FROM tbl_product_cart pc
		JOIN tbl_product p ON pc.Id = p.id
		JOIN tbl_cart c ON pc.cart_id = c.id WHERE c.user_id = 2
    */
    @Query("SELECT pc FROM ProductCart pc JOIN pc.cart c WHERE c.user.id = :userId")
    List<ProductCart> findAllProductFromCartByUser(Long userId);
}
