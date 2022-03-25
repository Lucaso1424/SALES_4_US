package com.copernic.cat.erp.sales_4_us.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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


    @Column(nullable = false)
    private Date date;

    @NotEmpty
    @Column(nullable = false)
    private int totalPrize;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @OneToMany
    @JoinColumn(name = "purchaseId", nullable = false)
    private List<LiniaPurchase> liniaPurchases;

}