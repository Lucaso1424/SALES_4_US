package com.copernic.cat.erp.sales_4_us.controllers;

import com.copernic.cat.erp.sales_4_us.models.Purchase;
import com.copernic.cat.erp.sales_4_us.models.User;
import com.copernic.cat.erp.sales_4_us.service.PurchaseService;
import com.copernic.cat.erp.sales_4_us.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private UserService userService;

    // Mètode GetMapping que retorna el llistat de productes de la BBDD
    @GetMapping("/purchase")
    public String inici(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User authenticatedUser = userService.findUserByEmail(authentication.getName());
        List<Purchase> purchases = purchaseService.listPurchases(authenticatedUser);
        model.addAttribute("listPurchases", purchases);
        return "purchase";
    }

}
