package com.copernic.cat.erp.sales_4_us.repository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptPassword {

    public static void main(String[] args) {

        var password = "jose";
        System.out.println("Contrasenya: " + password);
        System.out.println("Contrasenya encriptada:" + encriptarContrasenya(password));
    }

    public static String encriptarContrasenya(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}