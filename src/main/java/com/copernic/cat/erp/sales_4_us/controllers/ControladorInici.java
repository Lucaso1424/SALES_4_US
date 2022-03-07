/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.copernic.cat.erp.sales_4_us.controllers;

import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author lucas
 */
public class ControladorInici {

    @GetMapping("loginInici")
    public String inici() {
        return ("inici");
    }
}