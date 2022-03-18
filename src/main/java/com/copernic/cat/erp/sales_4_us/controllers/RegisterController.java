/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.copernic.cat.erp.sales_4_us.controllers;


import com.copernic.cat.erp.sales_4_us.models.Rol;
import com.copernic.cat.erp.sales_4_us.models.User;
import com.copernic.cat.erp.sales_4_us.repository.UserRepository;
import com.copernic.cat.erp.sales_4_us.service.UserService;
import com.copernic.cat.erp.sales_4_us.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RegisterController {

   /* @Autowired
    private UserRepository userRepo;

    @GetMapping("/register")
    public String registerForm(User user) {
        return "register";
    }

    @GetMapping("/register")
    public String inici() {
        return "register";
    }

    @PostMapping("/proces_register")
    public String registerProcessUser(User user) {
        userRepo.save(user);
        return "register_success";
    }*/

    @Autowired
    private UserService userService;

    @Autowired
    private Rol rol;

    @PostMapping("/proces_register")
    public String registerUser(@Valid User user, Errors errors, RedirectAttributes msg) {
        // Reload form if register has errors
        if (errors.hasErrors()) {
            return "register";
        }

        Utilities u = new Utilities();
        // Email no valido
        if (!u.checkDni(user.getDni())) {
            msg.addFlashAttribute("error", u.message("profile.error.dni"));
            return "redirect:/register";
        }

        // If mail exists in DB
        if (checkIfUserExist(user.getEmail())) {
            msg.addFlashAttribute("error", u.message("profile.error.emailAlreadyTaken"));
            return "redirect:/register";
        }

        // Ecrypt password
        user.setPassword(u.encryptPass(user.getPassword()));

        // Save on DB
        Rol defaultRole = rol.findByName("client");
        System.out.println(defaultRole.getName());
        user.addRol(defaultRole);
        userService.saveUser(user);
        return "redirect:/";
    }

    // Método para comprobar si el email ya existe en la DDBB
    private boolean checkIfUserExist(String email) {
        List<User> userList = userService.listUsers();
        for (User u : userList) {
            if (u.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

}