package com.copernic.cat.erp.sales_4_us.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class DashboardController {
    @GetMapping("/dashboard")
    public String inici() {
        return "main";
    }


}
