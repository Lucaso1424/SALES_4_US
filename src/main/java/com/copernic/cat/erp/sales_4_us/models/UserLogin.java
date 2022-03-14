package com.copernic.cat.erp.sales_4_us.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "USER")
public class UserLogin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private long userId;

    @Column(nullable = false, length = 20)
    private String userName;

    @NotEmpty
    @Column(nullable = false, length = 20)
    private String email;

    @NotEmpty
    @Column(nullable = false, length = 200)
    private String password;

    @OneToMany
    @JoinColumn(name = "userId")
    private List<Rol> rols;
    
}