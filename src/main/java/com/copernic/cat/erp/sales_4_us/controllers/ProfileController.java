package com.copernic.cat.erp.sales_4_us.controllers;

import com.copernic.cat.erp.sales_4_us.models.User;
import com.copernic.cat.erp.sales_4_us.service.UserService;
import com.copernic.cat.erp.sales_4_us.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String inici(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(authentication.getName());
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/updateProfile")
    public String updateProfile(
            User user,
            Errors errors,
            @RequestParam("fileImage") MultipartFile multipartFile
    ) throws IOException {
        if (errors.hasErrors()) {
            System.out.println(errors);
            return "/profile";
        }
        String fileName;
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
        return "redirect:/profile";
    }
}
