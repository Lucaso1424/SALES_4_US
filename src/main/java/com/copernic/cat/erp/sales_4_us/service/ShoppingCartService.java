package com.copernic.cat.erp.sales_4_us.service;

import com.copernic.cat.erp.sales_4_us.models.CartItem;
import com.copernic.cat.erp.sales_4_us.models.User;
import com.copernic.cat.erp.sales_4_us.repository.CartItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("shoppingCartService")
@Slf4j
public class ShoppingCartService {

    @Autowired
    private CartItemRepository cartItemRepository;


    public List<CartItem> listCartItems(User user) {
        List<CartItem> userCartItems = new ArrayList<>();
        for (CartItem c : cartItemRepository.findAll()){
            if (c.getUser().getUserId() == user.getUserId()) {
                userCartItems.add(c);
            }
        }
        return userCartItems;
    }

    public void addCartItem(CartItem cartItem){
        cartItemRepository.save(cartItem);
    }

    public CartItem searchCartItem(CartItem cartItem){
        return cartItemRepository.findById(cartItem.getId()).orElse(null);
    }

    public void deleteCart(CartItem cartItem){
        cartItemRepository.delete(cartItem);
    }


}
