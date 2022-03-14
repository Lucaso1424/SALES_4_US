/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.copernic.cat.erp.sales_4_us.controllers;

import com.copernic.cat.erp.sales_4_us.models.UserLogin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    //@GetMapping("/login")
    public String inici(Model model) {
        return "index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String login(@ModelAttribute(name="loginForm") UserLogin userLogin, Model model) {
        
        String email = userLogin.getEmail();
        String password = userLogin.getPassword();
        
        if ("jose@jose.local".equals(email) && "jose".equals(password)) {
            return "/dashboard";
        }
        
        model.addAttribute("loginError", true);
        return "index";
    }

    /*@GetMapping("/loginError")
    public String loginError(Model model) {
        return "redirect:login";
    }*/

}
