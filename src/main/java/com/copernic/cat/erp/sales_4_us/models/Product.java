package com.copernic.cat.erp.sales_4_us.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="id")
    private Integer id;

    @NotNull
    @Column(nullable = false)
    private Integer providerId;

    @NotEmpty
    @Column(nullable = false, length = 20)
    private String name;

    @NotNull
    @Column(nullable = false)
    private double prize;

    @NotEmpty
    @Column(nullable = false, length = 20)
    private String category;


    @Column(nullable = false, length = 500)
    private String description;

    /*private List<LiniaPurchase> liniaPurchases;

    private List<Opinion> opinions; */


}