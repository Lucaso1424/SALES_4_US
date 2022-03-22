package com.copernic.cat.erp.sales_4_us.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CRUDAdminController {

    @GetMapping("/crud_admin")
    public String inici(){
        return ("crud_admin");
    }
}
