package com.copernic.cat.erp.sales_4_us.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PurchaseController {

    // Mètode GetMapping que retorna el llistat de productes de la BBDD
    @GetMapping("/purchase")
    public String inici() {
        return "purchase";
    }
}
