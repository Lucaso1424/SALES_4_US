package com.copernic.cat.erp.sales_4_us.models;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "usuari")
public class UserLogin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUsuari;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @OneToMany
    @JoinColumn(name = "idUsuari")
    private List<Rol> rols;
}

