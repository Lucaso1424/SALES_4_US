package com.copernic.cat.erp.sales_4_us.models;

import java.io.Serializable;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private int id;

    @NotEmpty
    @Column(nullable = false, length = 20)
    private String name;

    @NotNull
    @Column(nullable = false)
    private double prize;

    @ManyToMany
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @Column(nullable = false, length = 500)
    private String description;

    @ManyToMany
    @JoinTable(
            name = "product_provider",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "provider_id")
    )
    private List<Provider> providers;

    /*private List<LiniaPurchase> liniaPurchases;

    private List<Opinion> opinions; */
}
