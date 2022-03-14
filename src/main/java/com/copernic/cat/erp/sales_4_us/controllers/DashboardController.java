package com.copernic.cat.erp.sales_4_us.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DashboardController {

    //@GetMapping("/dashboard")
    @PostMapping("/dashboard")
    public String inici() {
        return "main";
    }
}