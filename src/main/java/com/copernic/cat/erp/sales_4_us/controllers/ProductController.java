/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.copernic.cat.erp.sales_4_us.controllers;

import com.copernic.cat.erp.sales_4_us.models.Opinion;
import com.copernic.cat.erp.sales_4_us.models.Product;
import com.copernic.cat.erp.sales_4_us.models.User;
import com.copernic.cat.erp.sales_4_us.service.OpinionService;
import com.copernic.cat.erp.sales_4_us.service.ProductService;
import com.copernic.cat.erp.sales_4_us.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 *
 * @author lucas
 */
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OpinionService opinionService;

    @Autowired
    private UserService userService;

    @GetMapping("/product")
    public String iniciGet() {
        return "product";
    }

    @GetMapping("/product/{id}")
    public String showProduct(Product product, Model model) {
        Product p = productService.findProduct(product);
        //Opinion o = opinionService.listOpinion();

        model.addAttribute("product", p);
        model.addAttribute("opinion", new Opinion());
        model.addAttribute("opinions", p.getOpinions());
        return "product";
    }

    @PostMapping("/saveOpinion")
    public String saveOpinion(Opinion opinion, Errors errors) {
        if (errors.hasErrors()) {
            System.out.println(errors);
            return "redirect:/dashboard";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(authentication.getName());
        List<Product> products;

        products = productService.listProduct();
        Product p = new Product();

        System.out.println(opinion.getActualProduct() + "OOIOIOIOIOKOI");

        for (Product pro : products) {
            if (pro.getId() == opinion.getActualProduct()) {
                p = pro;
            }
        }

        opinion.setUser(user);
        opinionService.addOpinion(opinion);
        p.getOpinions().add(opinion);
        productService.addProduct(p);
        return "redirect:/product/" + p.getId();
    }

}