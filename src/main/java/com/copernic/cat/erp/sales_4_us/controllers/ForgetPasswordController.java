/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.copernic.cat.erp.sales_4_us.controllers;

import com.copernic.cat.erp.sales_4_us.models.PasswordResetToken;
import com.copernic.cat.erp.sales_4_us.models.Product;
import com.copernic.cat.erp.sales_4_us.models.User;
import com.copernic.cat.erp.sales_4_us.repository.PasswordResetTokenRepository;
import com.copernic.cat.erp.sales_4_us.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.util.Locale;
import java.util.UUID;


@Controller
public class ForgetPasswordController {

    @Autowired
    private UserService userService;
    @Autowired
    private MailSender mailSender;
    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @GetMapping("/forgot")
    public String inici() {
        return ("forgot");
    }

    @PostMapping("/forgot")
    public String inici_Post(
            @ModelAttribute(name = "user") User userEmail,
            Errors errors,
            RedirectAttributes msg,
            @RequestParam("fileImage") MultipartFile multipartFile
    ) throws IOException {
        User user = new User();
        var users = userService.listUsers();
        for (var u : users) {
            if (u.getEmail().equals(userEmail.getEmail())) {
                user = u;
            }
        }

        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiryDate(30);
        tokenRepository.save(token);

        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject("Reset Password");
        email.setText("/forgot?token=" + token);
        email.setTo(user.getEmail());
        email.setFrom("");
        mailSender.send(email);
        return "forgot";
    }

}
