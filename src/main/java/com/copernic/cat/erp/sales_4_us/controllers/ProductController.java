/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.copernic.cat.erp.sales_4_us.controllers;

import com.copernic.cat.erp.sales_4_us.models.Opinion;
import com.copernic.cat.erp.sales_4_us.models.Product;
import com.copernic.cat.erp.sales_4_us.service.OpinionService;
import com.copernic.cat.erp.sales_4_us.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/product")
    public String iniciGet() {
        return "product";
    }

    @GetMapping("/product/{id}")
    public String showProduct(Product product, Opinion opinion, Model model) {
        Product p = productService.findProduct(product);
        var opinions = opinionService.findOpinion(opinion);
        model.addAttribute("opinions", opinions);
        model.addAttribute("product", p);
        return "product";
    }

    /*@GetMapping("/product/{id}")
    public String showOpinion(Opinion opinion, Model model) {
        var opinions = opinionService.findOpinion(opinion);
        model.addAttribute("opinions", opinions);
        return "product";
    }*/

    @PostMapping("/saveOpinion")
    public String saveOpinion(Opinion opinion, Errors errors) {
        if (errors.hasErrors()) {
            System.out.println(errors);
            return "redirect:/product";
        }

        opinionService.addOpinion(opinion);
        return "redirect:/crud_client";
    }

}