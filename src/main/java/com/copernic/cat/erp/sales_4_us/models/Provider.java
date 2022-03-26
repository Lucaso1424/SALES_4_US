package com.copernic.cat.erp.sales_4_us.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "provider")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @NotEmpty
    @Column(nullable = false, length = 40)
    private String name;

    @NotEmpty
    @Column(nullable = false, length = 120)
    private String direction;

    @NotNull
    @Column( nullable = false)
    private int phone;

    @NotEmpty
    @Column(nullable = false, length = 40)
    private String website;

    @NotEmpty
    @Column(nullable = false, length = 40)
    private String email;

}