/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.copernic.cat.erp.sales_4_us.service;

import com.copernic.cat.erp.sales_4_us.models.Product;
import com.copernic.cat.erp.sales_4_us.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author lucas
 */
@Service("productService")
@Slf4j
public class ProductService  {

    // Importació de ProductRepository
    @Autowired
    private ProductRepository productRepository;

    // Mètode @Transactional, que retorna una llista de tots els productes
    @Transactional(readOnly = true)
    public List<Product> listProduct() {
        return productRepository.findAll();
    }

    // Mètode que retorna una llista dels productes per el text passat per paràmetre
    public List<Product> getByKeyword(String keyword){
        return productRepository.findByKeyword(keyword);
    }

    // Mètode per afegir un producte a la BBDD, utilitzant mètode save()
    public void addProduct(Product pro) {
        productRepository.save(pro);
    }

    // Mètode per esborrar un producte a la BBDD, utilitzant mètode delete()
    public void deleteProduct(Product pro) {
        productRepository.delete(pro);
    }

    // Mètode @Transactional, que retorna una producte utilitzant el mètode findById()
    @Transactional(readOnly = true)
    public Product findProduct(Product pro) {
        return productRepository.findById(pro.getId()).orElse(null);
    }


}
