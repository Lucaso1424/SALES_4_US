/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.copernic.cat.erp.sales_4_us.controllers;

import com.copernic.cat.erp.sales_4_us.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @GetMapping("/register")
    public String inici() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("proces_register")
    public String registerProcessUser() {
        return "register";
    }
}