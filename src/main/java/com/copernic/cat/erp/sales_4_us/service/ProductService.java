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
public class ProductService implements ProductServiceInterface {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional(readOnly=true)
    public List<Product> listProduct() {return productRepository.findAll(); }

    @Override
    public void addProduct(Product pro) {
        productRepository.save(pro);
    }

    @Override
    public void deleteProduct(Product pro) {
        productRepository.delete(pro);
    }

    @Override
    @Transactional(readOnly=true)
    public Product findProduct(Product pro) {
        return productRepository.findById(pro.getId()).orElse(null);
    }

}
