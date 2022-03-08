package com.copernic.cat.erp.sales_4_us.models;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "usuari")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id //L'atribut idUsuari és la clau primària de la BBDD
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Generació autonumèrica de l'id
    private long idUsuari;

    @NotEmpty//Validació perquè l'usuari afegeixi contingut al camp nom d'usuari
    private String email;

    @NotEmpty//Validació perquè l'usuari afegeixi contingut al camp contrasenya
    private String password;

    /*Implementem l'atribut que relacionarà l'usuari amb el rol, tenint en compte que un
     *usuari pot tenir més d'un rol, per tant serà una col.lecció de tipus list, on guardarem
     *tots els rols de l'usuari.
     */
    @OneToMany //Indica al sistema que la relació entre les taules usuari i rol en aquest cas és d'un a molts.
    @JoinColumn(name = "idUsuari") //Columna de la base de dades que farà de clau forana relacionant les dues taules.
    private List<Rol> rols;
}

