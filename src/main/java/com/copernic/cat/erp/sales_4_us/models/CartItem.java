package com.copernic.cat.erp.sales_4_us.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private Boolean visible;

    @Column
    private int quantity;
}