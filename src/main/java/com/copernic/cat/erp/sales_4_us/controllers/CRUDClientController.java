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
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

/**
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
        List<User> listUsers = userService.listClients();
        model.addAttribute("listUsers", listUsers);
        return "crud_list_client";
    }

    @GetMapping("/delete/{userId}")
    public String deleteClient(User user) {
        userService.deleteUser(user);
        return "redirect:/crud_client";
    }


    @GetMapping("/formClient")
    public String createClientForm(Model model) {
        model.addAttribute("user", new User());
        return "formClient";
    }

    @GetMapping("/formEditClient")
    public String editClientFrom(User user, Model model) {
        User u = userService.searchUser(user);
        model.addAttribute("user", u);
        return "formEditClient";
    }

    @PostMapping("saveEditClient")
    public String saveEditClient(
            User user,
            Errors errors,
            @RequestParam("fileImage") MultipartFile multipartFile
    ) throws IOException {
        if (errors.hasErrors()) {
            System.out.println(errors);
            return "/profile";
        }
        String fileName = null;
        if (multipartFile.getOriginalFilename() != null || !multipartFile.isEmpty()) {
            fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            user.setImage(fileName);
            String uploadDir = "./src/main/resources/static/images/user-image/" + user.getEmail();
            Path uploadPath = Paths.get(uploadDir);
            try (InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ioException) {
                throw new IOException("Could not save img " + fileName);
            }
        }
        Utilities u = new Utilities();
        user.setPassword(u.encryptPass(user.getPassword()));
        userService.addUser(user);
        return "redirect:/crud_client";
    }

    @PostMapping("/saveClient") //action = saveClient
    public String saveClient(User user, Errors errors, @RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
        if (errors.hasErrors()) {
            System.out.println(errors);
            return "formClient";
        }
        String fileName;
        if (multipartFile.getOriginalFilename() != null || !multipartFile.isEmpty()) {
            fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        } else {
            fileName = "default_profile.png";
        }
        user.setImage(fileName);
        String uploadDir = "./src/main/resources/static/images/user-image/" + user.getEmail();
        Path uploadPath = Paths.get(uploadDir);
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioException) {
            throw new IOException("Could not save img " + fileName);
        }
        Utilities u = new Utilities();
        user.setPassword(u.encryptPass(user.getPassword()));
        userService.addUser(user);
        return "redirect:/crud_client";
    }

    @GetMapping("/edit/{userId}")
    public String editClient(User user, Model model) {
        User u = userService.searchUser(user);
        model.addAttribute("user", u);
        return "formClient";
    }

}
