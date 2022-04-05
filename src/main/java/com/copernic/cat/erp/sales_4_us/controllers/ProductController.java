/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.copernic.cat.erp.sales_4_us.controllers;

import com.copernic.cat.erp.sales_4_us.models.Opinion;
import com.copernic.cat.erp.sales_4_us.models.Product;
import com.copernic.cat.erp.sales_4_us.models.User;
import com.copernic.cat.erp.sales_4_us.service.OpinionService;
import com.copernic.cat.erp.sales_4_us.service.ProductService;
import com.copernic.cat.erp.sales_4_us.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class ProductController {

    // Importació de Services d'Usuari, Opinions i Products

    @Autowired
    private ProductService productService;

    @Autowired
    private OpinionService opinionService;

    @Autowired
    private UserService userService;

    // Mètode GetMapping que retorna la view del producte
    @GetMapping("/product")
    public String iniciGet() {
        return "product";
    }

    // Mètode GetMapping que retorna el producte seleccionat per la ID
    @GetMapping("/product/{id}")
    public String showProduct(Product product, Model model) {
        // Creació d'un producte utilitzant mètode findProduct() de la BBDD
        Product p = productService.findProduct(product);
        //Opinion o = opinionService.listOpinion();

        // Addició de productes, i mètode getOpinions() per mostrar les opinions del producte
        model.addAttribute("product", p);
        model.addAttribute("opinion", new Opinion());
        model.addAttribute("opinions", p.getOpinions());
        return "product";
    }

    // Mètode PostMapping que guarda una opinió al producte de la base de dades
    @PostMapping("/saveOpinion")
    public String saveOpinion(Opinion opinion, Errors errors) {
        if (errors.hasErrors()) {
            System.out.println(errors);
            return "redirect:/dashboard";
        }

        // Guardem en un objecte user, l'usuari loguejat per guardar el camp de getName(),
        // com a usuari de l'opinió
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(authentication.getName());
        List<Product> products;

        // Realitzem un listProduct() per llistar els productes en la List<Product>
        products = productService.listProduct();
        Product p = new Product();

        System.out.println(opinion.getActualProduct() + "OOIOIOIOIOKOI");

        // For each per setejar la id del producte en l'opinio
        for (Product pro : products) {
            if (pro.getId() == opinion.getActualProduct()) {
                p = pro;
            }
        }

        // Setejem l'usuari en l'opinió
        opinion.setUser(user);
        // Setejem l'opinió en el llistat d'opinions
        opinionService.addOpinion(opinion);
        // Setejem l'opinió en el producte actual
        p.getOpinions().add(opinion);
        // Afegim el producte actualitzat amb l'opinió
        productService.addProduct(p);
        // Farem un redirect de la mateixa pàgina del producte amb el p.getId()
        // del producte per així visualitzar l'opinió creada
        return "redirect:/product/" + p.getId();
    }

}