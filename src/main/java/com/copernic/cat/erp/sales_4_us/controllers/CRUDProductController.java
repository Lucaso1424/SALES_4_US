package com.copernic.cat.erp.sales_4_us.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class CRUDProductController {
    @GetMapping("/crud_product")
    public String crudProduct(){
        return ("crud_product");
    }
}