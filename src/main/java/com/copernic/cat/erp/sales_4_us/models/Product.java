package com.copernic.cat.erp.sales_4_us.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private long id;

    @NotEmpty
    @Column(nullable = false, length = 20)
    private String name;

    @NotNull
    @Column(nullable = false)
    private double prize;

    /*@OneToMany
    @JoinColumn(name="id_category")*/
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @Column(nullable = false, length = 500)
    private String description;

    /*@ManyToOne
    @JoinColumn(name="providerId")
    private List<Provider> providerId; */

    /*private List<LiniaPurchase> liniaPurchases;

    private List<Opinion> opinions; */

}