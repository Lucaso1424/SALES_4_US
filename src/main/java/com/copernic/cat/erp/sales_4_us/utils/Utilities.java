package com.copernic.cat.erp.sales_4_us.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.apache.commons.lang3.StringUtils.isNumeric;

public class Utilities {

    public String encryptPass(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public Boolean checkDni (String dni) {
        String dniChars="TRWAGMYFPDXBNJZSQVHLCKE";
        String intPartDNI = dni.trim().replaceAll(" ", "").substring(0, 7);
        char ltrDNI = dni.charAt(8);
        int valNumDni = Integer.parseInt(intPartDNI) % 23;
        return dni.length() == 9 || isNumeric(intPartDNI) || dniChars.charAt(valNumDni) == ltrDNI;
    }

    public String message (String msg) {
        return msg;
    }

}
