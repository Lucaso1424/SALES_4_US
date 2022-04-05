package com.copernic.cat.erp.sales_4_us.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.apache.commons.lang3.StringUtils.isNumeric;

public class Utilities {

    // Mètode per generar una contrasenya encriptada, al crear
    // un usuari, o modificant-lo
    public String encryptPass(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    // Mètode que retorna una boolean per comprovar que
    // el DNI es vàlid, realitzant replace() i substring()
    // per substituir caràcters
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
