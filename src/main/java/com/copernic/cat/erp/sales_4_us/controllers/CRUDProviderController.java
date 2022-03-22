package com.copernic.cat.erp.sales_4_us.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class CRUDProviderController {
    @GetMapping("/crud_provider")
    public String crudProvider(){
        return ("crud_provider");
    }
}
