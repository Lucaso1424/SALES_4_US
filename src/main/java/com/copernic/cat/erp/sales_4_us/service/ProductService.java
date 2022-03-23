/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.copernic.cat.erp.sales_4_us.service;

import com.copernic.cat.erp.sales_4_us.models.Product;
import com.copernic.cat.erp.sales_4_us.repository.ProductRepository;
import com.copernic.cat.erp.sales_4_us.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lucas
 */
@Service 
public class ProductService implements ProductServiceInterface {
    
    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional(readOnly=true)
    public List<Product> llistarProduct() {
        return (List<Product>) productRepository.findAll(); 
    }
    
}
