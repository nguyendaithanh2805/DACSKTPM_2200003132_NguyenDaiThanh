package org.example.coffeeshopwebsite.controller.user;

import jakarta.servlet.http.HttpSession;
import org.example.coffeeshopwebsite.model.Cart;
import org.example.coffeeshopwebsite.model.Product;
import org.example.coffeeshopwebsite.model.ProductCart;
import org.example.coffeeshopwebsite.model.User;
import org.example.coffeeshopwebsite.service.CartService;
import org.example.coffeeshopwebsite.service.ProductCartService;
import org.example.coffeeshopwebsite.service.ProductService;
import org.example.coffeeshopwebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    private final CartService cartService;
    private final ProductCartService productCartService;
    @Autowired
    public CartController(CartService cartService, ProductCartService productCartService) {
        this.cartService = cartService;
        this.productCartService = productCartService;
    }

    @PostMapping("/cart-add")
    public String addProductToCart(@RequestParam Integer quantity, @RequestParam Long productId) {
        cartService.addProductToCart(quantity, productId);
        return "redirect:/menu";
    }

    @GetMapping("/cart-view")
    public String showCart(Model model) {
        List<ProductCart> productCarts = productCartService.getAllProductByUser();
        model.addAttribute("productCarts", productCarts);
        return "user/cart";
    }
}
