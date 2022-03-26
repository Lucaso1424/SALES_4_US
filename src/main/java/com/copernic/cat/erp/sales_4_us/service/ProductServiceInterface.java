/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.copernic.cat.erp.sales_4_us.service;

import com.copernic.cat.erp.sales_4_us.models.Product;
import java.util.List;

/**
 *
 * @author lucas
 */
public interface ProductServiceInterface {

    public List<Product> listProduct();

    public void addProduct(Product pro);

    public void deleteProduct(Product pro);

    public Product findProduct(Product pro);
}
