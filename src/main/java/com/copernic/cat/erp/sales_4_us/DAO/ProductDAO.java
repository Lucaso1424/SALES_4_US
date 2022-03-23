/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.copernic.cat.erp.sales_4_us.DAO;

import com.copernic.cat.erp.sales_4_us.models.Product;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author lucas
 */ 
public interface ProductDAO extends CrudRepository<Product,Long> {
    
}