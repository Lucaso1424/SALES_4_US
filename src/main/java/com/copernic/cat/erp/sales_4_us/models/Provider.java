package com.copernic.cat.erp.sales_4_us.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "provider")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false, length = 40)
    private String name;

    @Column(nullable = false, length = 120)
    private String direction;

    @Column( nullable = false)
    private Integer phone;

    @Column(nullable = false, length = 40)
    private String website;

    @Column(nullable = false, length = 40)
    private String email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> products;

}