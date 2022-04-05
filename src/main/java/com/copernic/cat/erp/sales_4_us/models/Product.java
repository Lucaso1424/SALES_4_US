package com.copernic.cat.erp.sales_4_us.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "product")
public class Product implements Serializable {

    // Id producte (camp BBDD)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private int id;

    // Nom del producte (camp BBDD)
    @NotEmpty
    @Column(nullable = false, length = 20)
    private String name;

    // Ruta de l'imatge (camp BBDD)
    @NotEmpty
    @Column(nullable = false, length = 20, name = "pathImage")
    private String pathImage;

    // Preu del producte (camp BBDD)
    @NotNull
    @Column(nullable = false)
    private double prize;

    // Nom de la imatge (camp BBDD)
    @Column(nullable = false, length = 64)
    private String image;

    // Relació amb nom de la categoria per al producte en un List<Category>
    @ManyToMany
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    // Descripció producte (camp BBDD)
    @Column(nullable = false, length = 500)
    private String description;

    // Relació amb id del provider
    @ManyToMany
    @JoinTable(
            name = "product_provider",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "provider_id")
    )
    private List<Provider> providers;

    // Relació amb les opinions d'un producte en format de List<Opinion>
    @ManyToMany
    @JoinTable(
            name = "product_opinions",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "opinion_id")
    )
    private List<Opinion> opinions;

}
