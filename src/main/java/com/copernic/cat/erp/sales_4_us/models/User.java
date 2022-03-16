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

/*    @Column(nullable = false, length = 20)
    private String userName;*/

    @NotEmpty
    @Column(nullable = false, length = 20)
    private String email;

    @NotEmpty
    @Column(nullable = false, length = 200)
    private String password;

    @OneToMany
    @JoinColumn(name = "user_Id")
    private List<Rol> rols;

    @Override
    public String toString(){
        return userId + " " + email +" " + password;
    }
    
}