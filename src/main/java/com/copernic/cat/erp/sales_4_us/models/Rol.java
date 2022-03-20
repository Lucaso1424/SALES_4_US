package com.copernic.cat.erp.sales_4_us.models;

import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Entity
@Table(name = "rol")
@Repository
public class Rol implements Serializable {

    private final String ROL_CLIENT = "client";
    private final String ROL_ADMIN = "admin";
    private final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRol;

    private String name;

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