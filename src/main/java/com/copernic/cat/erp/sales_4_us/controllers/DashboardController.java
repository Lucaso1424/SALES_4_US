package com.copernic.cat.erp.sales_4_us.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DashboardController {

    @PostMapping("/dashboard")
    public String inici() {
        return "home";
    }

    @GetMapping("/dashboard")
    public String iniciGet() {
        return "home";
    }
}
