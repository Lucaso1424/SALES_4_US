package com.copernic.cat.erp.sales_4_us.controllers;

import com.copernic.cat.erp.sales_4_us.models.Product;
import com.copernic.cat.erp.sales_4_us.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CRUDProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/crud_product")
    public String inici(Model model){
        List<Product> listProducts = productService.listProduct();
        model.addAttribute("listProducts", listProducts);
        return "crud_list_product";
    }

    @GetMapping("/delete/product/{id}")
    public String deleteProduct(Product product){
        productService.deleteProduct(product);
        return "redirect:/crud_product";
    }


    @GetMapping("/formProduct")
    public String createProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "formProduct";
    }

    @PostMapping("/saveProduct") //action = saveClient
    public String saveAdmin(Product product, Errors errors) {
        if (errors.hasErrors()) {
            System.out.println(errors);
            return "formAdmin";
        }
        productService.addProduct(product);
        return "redirect:/crud_product";
    }

    @GetMapping("/edit/product/{id}")
    public String editClient(Product product, Model model) {
        Product p = productService.findProduct(product);
        model.addAttribute("product", p);
        return "formProduct";
    }

}