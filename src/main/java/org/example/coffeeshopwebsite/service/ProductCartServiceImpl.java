package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Cart;
import org.example.coffeeshopwebsite.model.Product;
import org.example.coffeeshopwebsite.model.ProductCart;
import org.example.coffeeshopwebsite.repository.ProductCartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCartServiceImpl implements ProductCartService {
    private final Logger logger = LoggerFactory.getLogger(ProductCartServiceImpl.class);
    private final ProductCartRepository productCartRepository;

    @Autowired
    public ProductCartServiceImpl(ProductCartRepository productCartRepository) {
        this.productCartRepository = productCartRepository;
    }

    @Override
    public void saveProductCart(Cart cart, Product product, Integer quantity) {
        ProductCart productCart = new ProductCart();
        productCart.setCart(cart);
        productCart.setProduct(product);
        productCart.setQuantity(quantity);
        productCartRepository.save(productCart);
        logger.info("Saved product cart successfully");
    }
}
