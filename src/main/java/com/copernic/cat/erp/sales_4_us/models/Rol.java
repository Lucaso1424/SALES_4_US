package com.copernic.cat.erp.sales_4_us.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "rol")
//@Repository
public class Rol implements Serializable {

    // Final strings amb el nom del rol de l'usuari
    private final String ROL_CLIENT = "client";
    private final String ROL_ADMIN = "admin";

    // Serial Version ID
    private final long serialVersionUID = 1L;


    // Id del rol (camp BBDD)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRol;

    // Nom del rol (camp BBDD)
    private String name;

    // Mètode findByName per retornar el rol de l'usuari
    // utilitzant els finals strings del rol de l'usuari
    public Rol findByName(String rolName) {
        Rol generatedRol = new Rol();
        if (rolName.equals(ROL_CLIENT)){
            generatedRol.setName(ROL_CLIENT);
        }
        if (rolName.equals(ROL_ADMIN)){
            generatedRol.setName(ROL_ADMIN);
        }
        return generatedRol;
    }

}