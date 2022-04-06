package com.copernic.cat.erp.sales_4_us.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="id")
    private int id;


    @Temporal(TemporalType.DATE)
    @Column
    private Date date;

    @NotNull
    @Column(nullable = false)
    private double totalPrize;


    @ManyToMany
    @JoinTable(
            name = "purchase_cart",
            joinColumns = @JoinColumn(name = "purchase_id"),
            inverseJoinColumns = @JoinColumn(name = "cart_item")
    )
    private List<CartItem> cartItems;

}