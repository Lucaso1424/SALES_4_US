package com.copernic.cat.erp.sales_4_us.controllers;

import com.copernic.cat.erp.sales_4_us.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DashboardController {

    @Autowired
    private ProductService productService;

    @PostMapping("/dashboard")
    public String inici(Model model) {

        var products = productService.listProduct();
        model.addAttribute("products", products);

        return "home";
    }

    @GetMapping("/dashboard")
    public String iniciGet(Model model) {

        var products = productService.listProduct();
        model.addAttribute("products", products);

        return "home";
    }
}
