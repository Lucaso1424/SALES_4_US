package com.copernic.cat.erp.sales_4_us.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Utilities {

    public String encryptPass(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public Boolean checkDni (String dni) {
        return true;
    }

    public String message (String msg) {
        return msg;
    }

}
