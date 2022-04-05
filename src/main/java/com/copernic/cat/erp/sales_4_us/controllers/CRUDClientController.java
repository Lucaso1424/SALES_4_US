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
import org.springframework.web.bind.annotation.ModelAttribute;
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

@Controller
public class CRUDClientController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    //Llistem tots els clients
    @GetMapping("/crud_client")
    public String generateClientList(Model model) {
        List<User> listUsers = userService.listClients();
        model.addAttribute("listUsers", listUsers);
        return "crud_list_client";
    }

    //Eliminem el client seleccionat
    @GetMapping("/delete/{userId}")
    public String deleteClient(User user) {
        userService.deleteUser(user);
        return "redirect:/crud_client";
    }


    //Formulari per crear un client
    @GetMapping("/formClient")
    public String createClientForm(Model model) {
        model.addAttribute("user", new User());
        return "formClient";
    }

    //Formulari per editar un client ja existent
    @GetMapping("/formEditClient")
    public String editClientFrom(User user, Model model) {
        User u = userService.searchUser(user);
        model.addAttribute("user", u);
        return "formEditClient";
    }

    //Guardar els canvis d'un usuari ya creat
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
        String fileName;
        //Si en el formulari han enviat una foto es guardara al servidor y la BD
        if (multipartFile.getOriginalFilename() != null && !multipartFile.isEmpty()) {
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

    //Guardar un client a la BD quan es crea
    @PostMapping("/saveClient")
    public String saveClient(
            @ModelAttribute(name="user") User user,
            Errors errors,
            @RequestParam("fileImage") MultipartFile multipartFile
    ) throws IOException {
        if (errors.hasErrors()) {
            System.out.println(errors);
            return "formClient";
        }
        String fileName;
        User savedUser = userRepository.save(user);
        String uploadDir = "./src/main/resources/static/images/user-image/" + savedUser.getEmail();
        //Si no hi ha imatge en el formulari es guardara una per defecte
        if (multipartFile.getOriginalFilename() == null && multipartFile.isEmpty()){
            fileName = "default_profile.png";
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }
            try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream("./static/images/default_profile.png")) {
                Path filePath = uploadPath.resolve(fileName);
                assert inputStream != null;
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ioException){
                throw new IOException("Could not save img " + fileName);
            }
        } else {
            //Si s'ha pujat una imatge es guardara la imatge y el nom en la BD
            fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }
            try(InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ioException){
                throw new IOException("Could not save img " + fileName);
            }
        }
        user.setImage(fileName);
        Utilities u = new Utilities();
        user.setPassword(u.encryptPass(user.getPassword()));
        user.setRol("client");
        userService.addUser(user);

        return "redirect:/crud_client";
    }

    //Li pasem l'usuari que volem editar amb el seu id
    @GetMapping("/edit/{userId}")
    public String editClient(User user, Model model) {
        User u = userService.searchUser(user);
        model.addAttribute("user", u);
        return "formEditClient";
    }

}
