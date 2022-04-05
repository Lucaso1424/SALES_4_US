package com.copernic.cat.erp.sales_4_us.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "opinion")
public class Opinion implements Serializable {

    // Id opinió producte (camp BBDD)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

        // Relació amb id de l'usuari per a l'opinió
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Títol de l'opinió (camp BBDD)
    @Column(nullable = false, length = 40)
    private String title;

    // Descripció de l'opinió (camp BBDD)
    @Column(nullable = false, length = 400)
    private String description;

    // Estrelles per puntuar l'opinió amb int (camp BBDD)
    @Column(nullable = false)
    private int stars;

    // Guardar el producte (camp BBDD)
    @Transient
    private int actualProduct;

}