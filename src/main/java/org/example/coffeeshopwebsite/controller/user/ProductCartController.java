package org.example.coffeeshopwebsite.controller.user;

import org.example.coffeeshopwebsite.model.Order;
import org.example.coffeeshopwebsite.model.ProductCart;
import org.example.coffeeshopwebsite.service.ProductCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductCartController {
    private final ProductCartService productCartService;

    @Autowired
    public ProductCartController(ProductCartService productCartService) {
        this.productCartService = productCartService;
    }

    @GetMapping("/cart-view")
    public String showCart(Model model) {
        List<ProductCart> productCarts = productCartService.getAllProductByUser();
        model.addAttribute("productCarts", productCarts);
        model.addAttribute("order", new Order());
        return "user/cart";
    }

    @GetMapping("/cart-delete")
    public String deleteProductFromCartById(@RequestParam Long productCartId) {
        productCartService.deleteProductFromCartById(productCartId);
        return "redirect:/cart-view";
    }
}
