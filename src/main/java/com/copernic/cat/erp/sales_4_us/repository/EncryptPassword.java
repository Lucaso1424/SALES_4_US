package com.copernic.cat.erp.sales_4_us.repository;

import com.copernic.cat.erp.sales_4_us.utils.Utilities;

public class EncryptPassword {

    public static void main(String[] args) {
        Utilities u = new Utilities();

        String password = "client";
        System.out.println("Contrasenya: " + password);
        System.out.println("Contrasenya encriptada:" + u.encryptPass(password));
    }
}