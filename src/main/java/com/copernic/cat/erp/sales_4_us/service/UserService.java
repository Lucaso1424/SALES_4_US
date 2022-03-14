package com.copernic.cat.erp.sales_4_us.service;

import com.copernic.cat.erp.sales_4_us.models.Rol;
import com.copernic.cat.erp.sales_4_us.models.UserLogin;
import com.copernic.cat.erp.sales_4_us.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;

@Service ("userDetailsService")
@Slf4j
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserLogin usuari= userRepository.findUserByEmail(email);

        if(usuari==null){
            throw new UsernameNotFoundException(email);
        }

        var rols = new ArrayList<GrantedAuthority>();

        for(Rol rol: usuari.getRols()){
            rols.add(new SimpleGrantedAuthority(rol.getName()));
        }

        return new User(usuari.getEmail(), usuari.getPassword(), rols);
    }

}
