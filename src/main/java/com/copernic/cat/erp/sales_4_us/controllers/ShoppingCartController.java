package com.copernic.cat.erp.sales_4_us.controllers;

import com.copernic.cat.erp.sales_4_us.models.CartItem;
import com.copernic.cat.erp.sales_4_us.models.User;
import com.copernic.cat.erp.sales_4_us.service.ShoppingCartService;
import com.copernic.cat.erp.sales_4_us.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ShoppingCartController {

    /*@Autowired //BY Lucas
    private ProductService productService;*/
    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    /*@GetMapping("/cart") //BY Lucas
    public String inici() {
        return "cart";
    }

    @GetMapping("/cart/{id}") //BY Lucas
    public String showProduct(Product product, Model model) {
        Product p = productService.findProduct(product);
        model.addAttribute("product", p);
        return "cart";
    }*/
    @GetMapping("/cart")
    public String showShoppingCart(Model model,
          @AuthenticationPrincipal User user) {
        User u = userService.searchUser(user);
        List<CartItem> cartItems = shoppingCartService.listCartItems(u);
        model.addAttribute("cartItems", cartItems);
        return "cart";
    }


}
