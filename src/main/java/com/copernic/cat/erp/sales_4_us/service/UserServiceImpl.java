/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.copernic.cat.erp.sales_4_us.service;

import com.copernic.cat.erp.sales_4_us.models.Rol;
import com.copernic.cat.erp.sales_4_us.models.User;
import com.copernic.cat.erp.sales_4_us.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author lucas
 */
//@Service
public class UserServiceImpl {
/*
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username);
        
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        List<Rol> rols = new ArrayList<>();
        Rol rol = new Rol();
        rol.setName("admin");
        rol.setIdRol(1);
        rols.add(rol);
        user.setRols(rols);
        user.setEmail("jose@jose.local");
        System.out.println(user.toString());
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRols()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Rol> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }*/

}
