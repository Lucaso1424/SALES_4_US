package com.copernic.cat.erp.sales_4_us.controllers;

import com.copernic.cat.erp.sales_4_us.models.Product;
import com.copernic.cat.erp.sales_4_us.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {

    @Autowired
    private ProductService productService;

    @GetMapping("/cart")
    public String inici() {
        return "cart";
    }

    @GetMapping("/cart/{id}")
    public String showProduct(Product product, Model model) {
        Product p = productService.findProduct(product);
        model.addAttribute("product", p);
        return "cart";
    }
}
