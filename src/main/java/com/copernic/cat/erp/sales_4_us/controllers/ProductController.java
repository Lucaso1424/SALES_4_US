/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.copernic.cat.erp.sales_4_us.controllers;

import com.copernic.cat.erp.sales_4_us.service.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author lucas
 */
@Controller
public class ProductController {
    
    @GetMapping("/product")
    public String iniciGet() {
        return "product";
    }
    
    @PostMapping("/product")
    public String inici() {
        return "product";
    }
    
    
    
    
}
