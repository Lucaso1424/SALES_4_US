package com.copernic.cat.erp.sales_4_us.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManageController {

    // Mètode GetMapping que retorna la view del manage amb les 4
    // opcions de gestionar el ERP
    @GetMapping("/manage")
    public String inici() {
        return "manage";
    }
}
