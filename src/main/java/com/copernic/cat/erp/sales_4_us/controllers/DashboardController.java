package com.copernic.cat.erp.sales_4_us.controllers;

import com.copernic.cat.erp.sales_4_us.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

    @Autowired
    private ProductService productService;

    // Mètode PostMapping que retorna el llistat de productes de la BBDD
    @PostMapping("/dashboard")
    public String inici(Model model) {

        var products = productService.listProduct();
        model.addAttribute("products", products);

        return "home";
    }

    // Mètode RequestMapping que retorna, en base al text inserit al input el llistat de productes
    // amb una Query del mètode getByKeyword()
    @RequestMapping(path = {"/","/search"})
    public String iniciKeyword(Model model, String keyword) {
        if (keyword != null) {
            // Llista productes amb una query realitzant un SELECT, pel camp name
            var products = productService.getByKeyword(keyword);
            model.addAttribute("products", products);
        } else {
            // Llista tots els productes de la BBDD
            var products = productService.listProduct();
            model.addAttribute("products", products);}
        return "home";
    }


    // Mètode GetMapping que retorna el llistat de productes de la BBDD
    @GetMapping("/dashboard")
    public String iniciGet(Model model) {

        var products = productService.listProduct();
        model.addAttribute("products", products);

        return "home";
    }
}