package com.copernic.cat.erp.sales_4_us.controllers;

import com.copernic.cat.erp.sales_4_us.models.CartItem;
import com.copernic.cat.erp.sales_4_us.models.Product;
import com.copernic.cat.erp.sales_4_us.models.User;
import com.copernic.cat.erp.sales_4_us.service.ProductService;
import com.copernic.cat.erp.sales_4_us.service.ShoppingCartService;
import com.copernic.cat.erp.sales_4_us.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ShoppingCartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/cart")
    public String showShoppingCart(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User u = userService.findUserByEmail(authentication.getName());
        List<CartItem> cartItems = shoppingCartService.listCartItems(u);
        model.addAttribute("listcartItems", cartItems);
        return "cart";
    }

    @GetMapping("/updateCart/{id}/{quantity}")
    public String updateCart(CartItem cartItem, Errors errors){
        if (errors.hasErrors()) {
            System.out.println(errors);
            return "cart";
        }
        CartItem savedItem = shoppingCartService.searchCartItem(cartItem);
        if (savedItem.getQuantity() != cartItem.getQuantity() && cartItem.getQuantity() > 0){
            savedItem.setQuantity(cartItem.getQuantity());
            shoppingCartService.addCartItem(savedItem);
        }
        return "redirect:/cart";
    }

   @GetMapping("/delete/cartItem/{id}")
    public String deleteCart(CartItem cartItem){
        shoppingCartService.deleteCart(cartItem);
       return "redirect:/cart";
   }

    @GetMapping("/addItem/{id}")
    public String addCartItem(Product product){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User u = userService.findUserByEmail(authentication.getName());
        CartItem itemToAdd = new CartItem();
        itemToAdd.setUser(u);
        itemToAdd.setQuantity(1);
        Product productToAdd = productService.findProduct(product);
        itemToAdd.setProduct(productToAdd);
        shoppingCartService.addCartItem(itemToAdd);
        return "redirect:/cart";
    }

}
