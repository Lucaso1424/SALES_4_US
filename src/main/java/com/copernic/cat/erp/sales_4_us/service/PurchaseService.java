package com.copernic.cat.erp.sales_4_us.service;

import com.copernic.cat.erp.sales_4_us.models.CartItem;
import com.copernic.cat.erp.sales_4_us.models.Purchase;
import com.copernic.cat.erp.sales_4_us.models.User;
import com.copernic.cat.erp.sales_4_us.repository.PurchaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("purchaseService")
@Slf4j
public class PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    UserService userService;

    public void addPurchase(Purchase purchase){
        purchaseRepository.save(purchase);
    }

    public List<Purchase> listPurchases(User user){
        List<Purchase> allPurchases = purchaseRepository.findAll();
        List<Purchase> purchaseToList = new ArrayList<>();
        for (Purchase p : allPurchases) {
            for (CartItem c : p.getCartItems()){
               if (c.getUser().getUserId() == user.getUserId()){
                   //Comprovem que la purchase no esta afegida ja
                   if (!purchaseToList.contains(p)) {
                       purchaseToList.add(p);
                   }
               }
            }
        }
        return purchaseToList;
    }
}
