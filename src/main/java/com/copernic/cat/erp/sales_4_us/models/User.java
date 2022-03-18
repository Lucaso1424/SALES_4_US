package com.copernic.cat.erp.sales_4_us.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="user_Id")
    private long userId;


    @NotEmpty
    @Column(nullable = false, length = 50)
    private String surname;

    @NotEmpty
    @Column(nullable = false, length = 9)
    private String dni;

    @NotEmpty
    @Column(nullable = false, length = 20)
    private String email;

    @NotEmpty
    @Column(nullable = false, length = 9)
    private int phone;

    @NotEmpty
    @Column(nullable = false, length = 200)
    private String password;

    @NotEmpty
    @Column(nullable = false, length = 60)
    private String address;

    @OneToMany
    @JoinColumn(name = "user_Id")
    private List<Rol> rols;

    @Override
    public String toString(){
        return userId + " " + email +" " + password;
    }

    public void addRol(Rol rol){
        this.rols.add(rol);
    }
    
}