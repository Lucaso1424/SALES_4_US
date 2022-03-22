package com.copernic.cat.erp.sales_4_us.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class CRUDAdminController {

    @GetMapping("/crud_admin")
    public String crudAdmin(){
        return ("crud_admin");
    }
}
