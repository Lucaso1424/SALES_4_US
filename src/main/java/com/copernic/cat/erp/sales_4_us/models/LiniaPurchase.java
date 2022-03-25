package com.copernic.cat.erp.sales_4_us.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class LiniaPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="id")
    private int id;

    /*@ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
*/
}