/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.copernic.cat.erp.sales_4_us.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ForgetPasswordController {

    @GetMapping("/forgot")
    public String inici() {
        return ("forgot");
    }

    @PostMapping("/forgot")
    public String inici_Post() {
        return ("forgot");
    }

}
