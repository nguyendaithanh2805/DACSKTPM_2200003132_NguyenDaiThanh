package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Cart;
import org.example.coffeeshopwebsite.model.Product;
import org.example.coffeeshopwebsite.model.ProductCart;
import org.example.coffeeshopwebsite.model.User;
import org.example.coffeeshopwebsite.repository.ProductCartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCartServiceImpl implements ProductCartService {
    private final Logger logger = LoggerFactory.getLogger(ProductCartServiceImpl.class);
    private final ProductCartRepository productCartRepository;
    private final UserService userService;

    @Autowired
    public ProductCartServiceImpl(ProductCartRepository productCartRepository, UserService userService) {
        this.productCartRepository = productCartRepository;
        this.userService = userService;
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

    @Override
    public List<ProductCart> getAllProductByUser() {
        User user = userService.getCurrentUser();
        return productCartRepository.findAllProductFromCartByUser(user.getId());
    }

    @Override
    public void deleteProductFromCartById(Long productCartId) {
        productCartRepository.deleteById(productCartId);
        logger.info("Delete product cart success");
    }
}
