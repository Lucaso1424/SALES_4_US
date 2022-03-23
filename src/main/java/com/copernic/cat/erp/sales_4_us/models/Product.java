package com.copernic.cat.erp.sales_4_us.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class Product {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private long id;

    @NotEmpty
    @Column(nullable = false, length = 20)
    private String name;

    @NotEmpty
    @Column(nullable = false)
    private double prize;

    @NotEmpty
    @Column(nullable = false, length = 40)
    private String category;

    @NotEmpty
    @Column(nullable = false, length = 500)
    private String description;

}