/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.copernic.cat.erp.sales_4_us.controllers;

import com.copernic.cat.erp.sales_4_us.models.User;
import com.copernic.cat.erp.sales_4_us.repository.UserRepository;
import com.copernic.cat.erp.sales_4_us.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String generateUserList(Model model) {
        List<User> listUsers = userService.listUsers();
        model.addAttribute("listUsers", listUsers);
        return "crud_list_client";
    }

    @GetMapping("/delete/{userId}")
    public String deleteUser(User user){
        userService.deleteUser(user);
        return "redirect:/crud_client";
    }

}
