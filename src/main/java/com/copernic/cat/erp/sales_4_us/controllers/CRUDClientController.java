/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.copernic.cat.erp.sales_4_us.controllers;

import com.copernic.cat.erp.sales_4_us.models.User;
import com.copernic.cat.erp.sales_4_us.repository.UserRepository;
import com.copernic.cat.erp.sales_4_us.service.UserService;
import com.copernic.cat.erp.sales_4_us.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 *
 * @author lucas
 */
@Controller
public class CRUDClientController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/crud_client")
    public String generateClientList(Model model) {
        List<User> listUsers = userService.listUsers();
        model.addAttribute("listUsers", listUsers);
        return "crud_list_client";
    }

    @GetMapping("/delete/{userId}")
    public String deleteClient(User user){
        userService.deleteUser(user);
        return "redirect:/crud_client";
    }


    @GetMapping("/formClient")
    public String createClientForm(Model model) {
        model.addAttribute("user", new User());
        return "formClient";
    }

    @PostMapping("/saveClient") //action = saveClient
    public String saveClient(User user, Errors errors) {
        if (errors.hasErrors()) {
            System.out.println(errors);
            return "formClient";
        }
        Utilities u = new Utilities();
        user.setPassword(u.encryptPass(user.getPassword()));
        userService.addUser(user);
        userRepository.save(user);
        return "redirect:/crud_client";
    }

    @GetMapping("/edit/{userId}")
    public String editClient(User user, Model model) {
        User u = userService.searchUser(user);
        model.addAttribute("user", u);
        return "formClient";
    }

}
