package com.copernic.cat.erp.sales_4_us.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Entity
@Table(name="rol")
public class Rol implements Serializable {

    private static final long serialVersionUID=1L;

    @Id //L'atribut idRol és la clau primària de la BBDD
    @GeneratedValue(strategy= GenerationType.IDENTITY) //Generació autonumèrica de l'id
    private long idRol;

    @NotEmpty//Validació perquè l'usuari afegeixi contingut al camp nom
    private String nom;
}